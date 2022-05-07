package org.triathlongirls.doranssam.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.triathlongirls.doranssam.domain.user.RefreshToken;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.dto.*;
import org.triathlongirls.doranssam.jwt.JwtTokenProvider;
import org.triathlongirls.doranssam.repository.RefreshTokenRepository;
import org.triathlongirls.doranssam.repository.UserRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public UserResponseDto signup(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByUsername(signupRequestDto.getUsername())) {
            throw new RuntimeException("이미 가입한 사용자입니다.");
        }

        // 비밀번호 일치 확인
        if (!signupRequestDto.getPassword1().equals(signupRequestDto.getPassword2())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        User user = signupRequestDto.toUser(passwordEncoder);
        return UserResponseDto.of(userRepository.save(user));
    }

    @Transactional
    public JwtTokenDto login(LoginRequestDto loginRequestDto) {

        // ID/PW 검증 후, 인증 정보로 JwtToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        JwtTokenDto jwtTokenDto = jwtTokenProvider.generateTokenDto(authentication);

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(jwtTokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto reissue(JwtTokenRequestDto jwtTokenRequestDto) {

        // RefreshToken 검증 후, 인증 정보로 대조
        if (!jwtTokenProvider.validateToken(jwtTokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }
        Authentication authentication = jwtTokenProvider.getAuthentication(jwtTokenRequestDto.getAccessToken());
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
        if (!refreshToken.getValue().equals(jwtTokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // JwtToken 재발행
        JwtTokenDto jwtTokenDto = jwtTokenProvider.generateTokenDto(authentication);
        RefreshToken newRefreshToken = refreshToken.updateValue(jwtTokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return jwtTokenDto;
    }
}