package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.LearningHistory;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningHistoryRepository extends JpaRepository<LearningHistory, Long> {
    boolean existsLearningHistoryByUserAndCourse(User user, Course course);
}
