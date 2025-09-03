package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Comment;
import lombok.Builder;

@Builder
public class CommentResponseDto {
    @JsonProperty("comment_id")
    private Long id;

    @JsonProperty("content")
    private String content;

    public static CommentResponseDto fromEntity(Comment comment){
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }
}
