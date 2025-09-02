package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Comment;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Builder
public class PostDetailResponseDto {
    @JsonProperty("post_id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("is_Study")
    private Boolean isStudy;

    @JsonProperty("user_id")
    private Long userId;

    public static PostDetailResponseDto fromEntity(Post post, Long userId) {
        return PostDetailResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .isStudy(post.getIsStudy())
                .userId(userId)
                .build();
    }
}
