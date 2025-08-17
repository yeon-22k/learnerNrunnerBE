package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Tag;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag, Long> {
    boolean existsByUserAndTag(User user, Tag tag);

    @Query("SELECT ut FROM UserTag ut JOIN ut.tag WHERE ut.user = :user")
    List<UserTag> findByUserWithTag(@Param("user") User user);
}
