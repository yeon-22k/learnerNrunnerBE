package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.dto.CourseSearchResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.UserTag;

import java.util.List;

public interface CourseSearchService {
    void indexCourses(List<Course> courses);
    List<CourseSearchResponseDto> searchByKeyword(String keyword);
    List<CourseSearchResponseDto> recommandByTags(List<UserTag> userTags);
    }
