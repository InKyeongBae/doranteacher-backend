package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.triathlongirls.doranssam.dto.*;
import org.triathlongirls.doranssam.service.user.AuthService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<UserResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return new ApiResponse<UserResponseDto>().ok(List.of(authService.signup(signupRequestDto)));
    }

    @PostMapping("/login")
    public ApiResponse<JwtTokenDto> login(@Valid @RequestBody LoginRequestDto loginDto) {
        return new ApiResponse<JwtTokenDto>().ok(List.of(authService.login(loginDto)));
    }

    @PostMapping("/reissue")
    public ApiResponse<JwtTokenDto> reissue(@RequestBody JwtTokenRequestDto jwtTokenRequestDto) {
        return new ApiResponse<JwtTokenDto>().ok(List.of(authService.reissue(jwtTokenRequestDto)));
    }
}
