package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private LocalDateTime createdAt;

    public UserTag(User user, Tag tag) {
        this.user = user;
        this.tag = tag;
        this.createdAt = LocalDateTime.now();
    }
}
