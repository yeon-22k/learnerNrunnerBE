package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.controller;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.service.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthServiceImpl authService;
    private UserRepository userRepository;

    public ResponseEntity<ApiResponse<T>> login(@RequestHeader String token,
                                                @RequestParam String email,
                                                @RequestParam String password){

    }



}
