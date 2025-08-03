package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
