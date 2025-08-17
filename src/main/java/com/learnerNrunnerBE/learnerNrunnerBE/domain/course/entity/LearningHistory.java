package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class LearningHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public LearningHistory(User user, Course course) {
        this.user = user;
        this.course = course;
        this.startedAt = LocalDateTime.now();
        this.progressRate = BigDecimal.ZERO;
        this.completeddAt = null;
    }
}
