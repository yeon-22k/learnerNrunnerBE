package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto dto, User user, Post post){
        this.content = dto.getContent();
        this.user = user;
        this.post = post;
    }
}
