package org.triathlongirls.doranssam.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.domain.user.WritingStep;
import org.triathlongirls.doranssam.dto.UserRequestDto;
import org.triathlongirls.doranssam.dto.UserResponseDto;
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


    @Transactional(readOnly = false)
    public UserResponseDto patchUser(UserRequestDto userRequestDto) {
        User user = userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new EntityNotFoundException("로그인 사용자 정보가 없습니다."));
        if(StringUtils.isNotBlank(userRequestDto.getNickname()))
            user.setNickname(userRequestDto.getNickname());
        user.setWritingStep(WritingStep.values()[userRequestDto.getWritingStep() - 1]);
        userRepository.save(user);
        return UserResponseDto.of(user);
    }
    
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

}
