package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Comment;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.repository.CommentRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.repository.PostRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserRepository;
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
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDto> getComments(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException.NotFoundException(ErrorCode.NOT_FOUND));
        return commentRepository.findByPost(post).stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }

    @Override
    public void createComment(CommentRequestDto dto, User user, Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException.NotFoundException(ErrorCode.NOT_FOUND));
        Comment comment = new Comment(dto, user, post);
        commentRepository.save(comment);
    }

    //댓글 수정 불가 > 관련 메서드 없음

    @Override
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
