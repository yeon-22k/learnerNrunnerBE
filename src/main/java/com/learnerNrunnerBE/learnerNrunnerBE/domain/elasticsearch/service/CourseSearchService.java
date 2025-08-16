package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;

import java.util.List;

public interface CourseSearchService {
    void indexCourses(List<Course> courses);
}
