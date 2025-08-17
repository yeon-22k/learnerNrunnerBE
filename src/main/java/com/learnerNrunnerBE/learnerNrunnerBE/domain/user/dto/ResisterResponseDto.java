package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResisterResponseDto {
    private String accessToken;
    private String refreshToken;
}
