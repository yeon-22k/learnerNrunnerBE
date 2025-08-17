package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.dto.CourseDetailResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.dto.CourseListResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.repository.CourseRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.dto.CourseSearchResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.service.CourseSearchService;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.UserTag;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserTagRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.service.UserTagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final RestTemplate restTemplate;
    private final CourseSearchService courseSearchService;
    private final TagService tagService;
    private final UserTagRepository userTagRepository;

    @Value("&{kmooc.api.serviceKey}")
    private String serviceKey;
    private final String BASE_URL = "https://apis.data.go.kr/B552881/kmooc_v2_0";
    private final String LIST_PATH = "/courseList_v2_0";
    private final String DETAIL_PATH = "/courseDetail_v2_0";


    public List<CourseSearchResponseDto> getHomeCourses(User user){
        List<UserTag> userTags = userTagRepository.findAll();

//        courseSearchService.recommendByTags(userTags);
//        > 이거 dto로 변환하고 반환
        return null;
    }

    @Override
    public int fetchAndSaveKmoocCourses() {
        if (courseRepository.count() > 0) {
            log.warn("DB에 이미 데이터가 존재하여 작업을 건너뜁니다.");
            return 0;
        }

        // 1. 강좌 목록 API 호출
        log.info("1단계: 강좌 목록 조회를 시작합니다...");
        String listApiUrl = BASE_URL + LIST_PATH + "?ServiceKey=" + serviceKey;
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
            String detailApiUrl = BASE_URL + DETAIL_PATH + "?ServiceKey=" + serviceKey + "&CourseId=" + courseId;
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

        if (coursesToSave.isEmpty()) {
            return 0; //**예외처리 해야함
        }

        //강의에 category와
        coursesToSave.forEach(course -> {
            if (course.getCategory() == null && !course.getCategory().isBlank()) {
                tagService.findOrCraateTag(course.getCategory());
            }
            if (course.getTag() != null && !course.getTag().isBlank()) {
                tagService.findOrCraateTag(course.getTag());
            }
        });

        // 3. DB에 한번에 저장
        log.info("3단계: 조회된 {}개의 상세 정보를 DB에 저장합니다...", coursesToSave.size());
        List<Course> saveCourses = courseRepository.saveAll(coursesToSave);
        log.info("DB 저장 완료!");

        // 4. ElasticSearch 에 색인
        courseSearchService.indexCourses(saveCourses);

        return saveCourses.size();
    }


}
