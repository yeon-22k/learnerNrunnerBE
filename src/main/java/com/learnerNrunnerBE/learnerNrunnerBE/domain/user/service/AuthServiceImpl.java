package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.ResisterRequestDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.dto.ResisterResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserRepository;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorCode;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception.CustomException;
import com.learnerNrunnerBE.learnerNrunnerBE.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public ResisterResponseDto register(ResisterRequestDto request){
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException.BadRequestException(ErrorCode.BAD_REQUEST);
        }

        User user = new User(request, passwordEncoder);
        userRepository.save(user);
        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken();

        return new ResisterResponseDto(accessToken, refreshToken);
    }

    public void login()
}
