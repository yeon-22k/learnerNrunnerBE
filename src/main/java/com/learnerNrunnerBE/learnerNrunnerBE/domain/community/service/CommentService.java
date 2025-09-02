package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getComments(Post post);
    void createComment(CommentRequestDto dto, User user, Post post);
    //댓글 수정 불가
    void deleteComment(Long commentId);
}
