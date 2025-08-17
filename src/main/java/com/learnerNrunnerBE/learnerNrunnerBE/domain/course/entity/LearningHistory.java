package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class LearningHistory {
    @Id
    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime completeddAt;
    private BigDecimal progressRate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
