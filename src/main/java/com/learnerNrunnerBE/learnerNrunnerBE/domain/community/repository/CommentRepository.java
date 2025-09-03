package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Comment;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
