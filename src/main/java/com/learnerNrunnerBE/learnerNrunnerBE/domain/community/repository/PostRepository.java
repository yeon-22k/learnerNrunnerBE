package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
