package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.controller;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.LoginRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.ResisterRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.ResisterResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.UserInfoDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.service.AuthServiceImpl;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.*;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private AuthServiceImpl authService;
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ResisterResponseDto>> register(@RequestBody ResisterRequestDto requestDto){
            ResisterResponseDto result = authService.register(requestDto);
            return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.CREATED, result));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserInfoDto>> login(@RequestHeader String token,
                                                          @RequestBody LoginRequestDto requestDto){
        UserInfoDto result = authService.login(requestDto);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK, result));
    }



}
