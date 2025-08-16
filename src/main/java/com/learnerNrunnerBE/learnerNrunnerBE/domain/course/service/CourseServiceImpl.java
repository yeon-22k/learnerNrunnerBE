package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.dto.CourseDetailResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.dto.CourseListResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl {
    private final CourseRepository courseRepository;
    private final RestTemplate restTemplate;

    @Value("&{kmooc.api.serviceKey}")
    private String serviceKey;
    private final String LIST_URL = "http://www.kmooc.kr/openapi/courseList";
    private final String DETAIL_URL = "http://www.kmooc.kr/openapi/courseDetail";

    @Transactional
    public int fetchAndSaveKmoocCourses() {
        if (courseRepository.count() > 0) {
            log.warn("DB에 이미 데이터가 존재하여 작업을 건너뜁니다.");
            return 0;
        }

        // 1. 강좌 목록 API 호출
        log.info("1단계: 강좌 목록 조회를 시작합니다...");
        String listApiUrl = LIST_URL + "?ServiceKey=" + serviceKey;
        CourseListResponseDto listResponse = restTemplate.getForObject(listApiUrl, CourseListResponseDto.class);

        if (listResponse == null || listResponse.getResult() == null) {
            log.error("강좌 목록을 가져오지 못했습니다.");
            return 0;
        }
        List<String> courseIds = listResponse.getResult().stream()
                .map(CourseListResponseDto.CourseListItem::getId)
                .toList();
        log.info("총 {}개의 강좌 ID를 확보했습니다.", courseIds.size());

        // 2. 각 강좌 상세 정보 API 호출
        log.info("2단계: 각 강좌의 상세 정보 조회를 시작합니다...");
        List<Course> coursesToSave = new ArrayList<>();
        for (String courseId : courseIds) {
            String detailApiUrl = DETAIL_URL + "?ServiceKey=" + serviceKey + "&CourseId=" + courseId;
            try {
                CourseDetailResponseDto detailResponse = restTemplate.getForObject(detailApiUrl, CourseDetailResponseDto.class);
                if (detailResponse != null) {
                    coursesToSave.add(detailResponse.toEntity());
                }
                Thread.sleep(50); // API 서버 부하 방지를 위한 짧은 대기
            } catch (Exception e) {
                log.error("강좌 상세 정보 조회 실패 (Course ID: {}): {}", courseId, e.getMessage());
            }
        }

        // 3. DB에 한번에 저장
        log.info("3단계: 조회된 {}개의 상세 정보를 DB에 저장합니다...", coursesToSave.size());
        courseRepository.saveAll(coursesToSave);
        log.info("DB 저장 완료!");

        return coursesToSave.size();
    }

}
