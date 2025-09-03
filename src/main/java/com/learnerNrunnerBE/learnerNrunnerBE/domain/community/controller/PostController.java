package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.controller;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.*;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service.PostService;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// todo : CurrentUser 어노테이션 구현해서 적용하기(모든 컨트롤러) / 로그인 후 사용가능 여부도 생각(아마 대부분 필요할 것)
// todo : 예외처리 생각

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/allPost")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK, posts));
    }

    @GetMapping("/{identifier}/posts")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getUserPosts(@PathVariable UUID identifier) {
        List<PostResponseDto> posts = postService.getUserPosts(identifier);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK, posts));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDetailResponseDto>> getPostDetail(@PathVariable Long postId) {
        PostDetailResponseDto post = postService.getPostDetail(postId);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK, post));
    }

    @PostMapping("/post")
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody PostRequestDto dto, User user) {
        postService.createPost(dto, user);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.CREATED));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> updatePost(@RequestBody PostRequestDto dto,
                                                        @PathVariable Long postId) {
        postService.updatePost(dto, postId);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK));
    }
}
