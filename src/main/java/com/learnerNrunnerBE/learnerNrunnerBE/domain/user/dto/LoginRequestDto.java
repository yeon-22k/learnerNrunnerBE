package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    String email;
    String password;
}
