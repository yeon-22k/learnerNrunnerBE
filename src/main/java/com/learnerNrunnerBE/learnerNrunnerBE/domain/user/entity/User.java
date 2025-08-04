package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID identifier;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private Long age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDateTime createAt = LocalDateTime.now();

}
