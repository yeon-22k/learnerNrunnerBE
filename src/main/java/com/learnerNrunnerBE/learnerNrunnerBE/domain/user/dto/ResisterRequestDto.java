package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResisterRequestDto {
    private String email;
    private String password;
    private String name;
    private Long age;
    private Gender gender;
}
