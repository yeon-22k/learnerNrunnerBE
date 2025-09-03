package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostDetailResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.PostResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.repository.PostRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorCode;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//todo : validation 체크

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    //메인화면 포스터 불러오기용
    @Override
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    //유저 포스터 검색용
    @Override
    public List<PostResponseDto> getPosts(User user){
        return postRepository.findByUser(user).stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    @Override
    public PostDetailResponseDto getPost(Long postId){
        return postRepository.findById(postId)
                .map(PostDetailResponseDto::fromEntity)
                .orElseThrow(() -> new CustomException.NotFoundException(ErrorCode.NOT_FOUND));
    }

    @Override
    public void createPost(PostRequestDto dto, User user){
        Post post = new Post(dto, user);
        postRepository.save(post);
    }

    @Override
    public void updatePost(PostRequestDto dto, Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException.NotFoundException(ErrorCode.NOT_FOUND));
        post.updatePost(dto);
    }

    @Override
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}
