package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Comment;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import lombok.Builder;

@Builder
public class CommentRequestDto {
    @JsonProperty("content")
    private String content;

//    @JsonProperty("user_id")
//    private Long userId;
//
//    @JsonProperty("post_id")
//    private Long postId;

    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .content(this.content)
                .user(user)
                .post(post).build();
    }



}
