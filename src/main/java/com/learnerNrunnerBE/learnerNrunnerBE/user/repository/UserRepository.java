package com.learnerNrunnerBE.learnerNrunnerBE.user.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
