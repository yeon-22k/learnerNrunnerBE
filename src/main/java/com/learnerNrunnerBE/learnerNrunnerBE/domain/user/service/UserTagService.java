package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

public interface UserTagService {
    void addTagsToUserFromCourse(User user, Course course);
}
