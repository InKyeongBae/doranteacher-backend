package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.triathlongirls.doranssam.dto.JwtTokenDto;
import org.triathlongirls.doranssam.dto.JwtTokenRequestDto;
import org.triathlongirls.doranssam.dto.UserRequestDto;
import org.triathlongirls.doranssam.dto.UserResponseDto;
import org.triathlongirls.doranssam.service.user.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.signup(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.login(userRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<JwtTokenDto> reissue(@RequestBody JwtTokenRequestDto jwtTokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(jwtTokenRequestDto));
    }
}
