package org.triathlongirls.springboot.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.springboot.domain.user.User;
import org.triathlongirls.springboot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        validateDuplicatieUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicatieUser(User user) {
        List<User> fineUsers = userRepository.findByUsername(user.getUsername());
        if (!fineUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
