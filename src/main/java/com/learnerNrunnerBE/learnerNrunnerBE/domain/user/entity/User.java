package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.ResisterRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//todo : column 들 이름 지정(모든 엔티티 대하여)

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BINARY(16)", nullable = false, unique = true)
    private UUID identifier;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private Long age;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTag> tags;

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
