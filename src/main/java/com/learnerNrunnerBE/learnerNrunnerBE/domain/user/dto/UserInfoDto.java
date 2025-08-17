package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;

import java.util.UUID;

public class UserInfoDto {
    private UUID identifier;
    private String accessToken;
    private String refreshToken;

    public UserInfoDto(User user, String accessToken, String refreshToken) {
        this.identifier = user.getIdentifier();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
