package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

import java.util.UUID;

public class UserInfoDto {
    private UUID identifier;
    private String email;
    private String password;
    private String name;

    public UserInfoDto(User user) {
        this.identifier = user.getIdentifier();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
    }
}
