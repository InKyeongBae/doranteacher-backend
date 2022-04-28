package org.triathlongirls.doranssam.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.repository.UserRepository;
import org.triathlongirls.doranssam.web.dto.UserResponseDto;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(String username) {
        return userRepository.findByUsername(username)
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("사용자 정보가 없습니다."));
    }

    @Transactional(readOnly = true)
    public UserResponseDto getMyInfo() {
        return userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 사용자 정보가 없습니다."));
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
