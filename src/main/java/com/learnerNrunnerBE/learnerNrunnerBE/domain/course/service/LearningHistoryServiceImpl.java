package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.dto.LearningHistoryResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.LearningHistory;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.repository.CourseRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.repository.LearningHistoryRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserTagRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.service.UserTagService;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorCode;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LearningHistoryServiceImpl implements LearningHistoryService {
    private final LearningHistoryRepository learningHistoryRepository;
    private final UserRepository userRepository;
    private final UserTagService userTagService;
    private final CourseRepository courseRepository;

    @Override
    public void startCourse(User user, Long courseId){
        Course course = courseRepository.findById(courseId).get(); //**없을때 예외처리

        if (learningHistoryRepository.existsLearningHistoryByUserAndCourse(user, course)){
            throw new CustomException.BadRequestException(ErrorCode.BAD_REQUEST);
        }

        LearningHistory learningHistory = new LearningHistory(user, course);
        learningHistoryRepository.save(learningHistory);
        log.info("사용자 ID {}: 강의 {} 수강을 시작합니다.", user.getId(), courseId);

        userTagService.addTagsToUserFromCourse(user, course);
    }

    @Override
    public void updateProgress(User user, Long courseId, BigDecimal progress){

    }

}
