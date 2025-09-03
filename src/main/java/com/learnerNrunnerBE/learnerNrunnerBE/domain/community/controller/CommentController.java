package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.controller;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service.CommentService;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getComments(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.getComments(postId);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK, comments));

    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<ApiResponse<Void>> createComment(@RequestBody CommentRequestDto dto,
                                                           @PathVariable Long postId,  User user) {
        commentService.createComment(dto, user, postId);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.CREATED));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK));
    }
}
