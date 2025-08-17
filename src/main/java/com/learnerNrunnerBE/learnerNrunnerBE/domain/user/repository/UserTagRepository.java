package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag, Long> {
}
