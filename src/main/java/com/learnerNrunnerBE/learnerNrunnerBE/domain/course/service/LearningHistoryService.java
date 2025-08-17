package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

import java.math.BigDecimal;

public interface LearningHistoryService {
    void startCourse(User user, Long courseId);
//    void completeCourse(Course course); ** 구현해야함
    void updateProgress(User user, Long courseId, BigDecimal progress);
}
