package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostDetailResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostResponseDto> getAllPosts();
    List<PostResponseDto> getUserPosts(UUID identifier);
    PostDetailResponseDto getPostDetail(Long postId);
    void createPost(PostRequestDto dto, User user);
    void updatePost(PostRequestDto dto, Long postId);
    void deletePost(Long postId);
}
