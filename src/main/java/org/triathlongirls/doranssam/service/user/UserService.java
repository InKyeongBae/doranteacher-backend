package org.triathlongirls.doranssam.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.dto.UserResponseDto;
import org.triathlongirls.doranssam.dto.WritingStepDto;
import org.triathlongirls.doranssam.exception.EntityNotFoundException;
import org.triathlongirls.doranssam.repository.UserRepository;
import org.triathlongirls.doranssam.util.SecurityUtil;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto getMyInfo() {
        return userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new EntityNotFoundException("로그인 사용자 정보가 없습니다."));
    }
/*
    @Transactional(readOnly = false)
    public WritingStepDto updateWritingStep(WritingStepDto writingStepDto) {
        User user = userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new RuntimeException("로그인 사용자 정보가 없습니다."));
        return user.updateWritingStep(writingStepDto.getWritingStep())
                .map;
    }
*/
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
