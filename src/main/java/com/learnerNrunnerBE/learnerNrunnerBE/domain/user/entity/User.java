package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.ResisterRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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

    @PrePersist
    protected void onCreate() {
        this.identifier = UUID.randomUUID();
        this.createAt = LocalDateTime.now();
    }

    public User(ResisterRequestDto requestDto, PasswordEncoder passwordEncoder) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.password = passwordEncoder.encode(requestDto.getPassword());
        this.age = requestDto.getAge();
        this.gender = requestDto.getGender();
    }

}
