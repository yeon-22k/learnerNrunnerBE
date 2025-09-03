package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostDetailResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

import java.util.List;

public interface PostService {
    List<PostResponseDto> getAllPosts();
    List<PostResponseDto> getPosts(User user);
    PostDetailResponseDto getPost(Long postId);
    void createPost(PostRequestDto dto, User user);
    void updatePost(PostRequestDto dto, Long postId);
    void deletePost(Long postId);
}
