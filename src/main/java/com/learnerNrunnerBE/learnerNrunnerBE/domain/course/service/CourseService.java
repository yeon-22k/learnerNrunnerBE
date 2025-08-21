package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.dto.CourseSearchResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

import java.util.List;

public interface CourseService {
    List<CourseSearchResponseDto> getHomeCourses(User user);
    int fetchAndSaveKmoocCourses();
}
