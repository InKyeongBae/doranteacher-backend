package org.triathlongirls.springboot.service.diaries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.springboot.domain.user.User;
import org.triathlongirls.springboot.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    
    @Test
    public void 회원가입() throws Exception {
        //given
        User user = new User();

        //when
        Long savedId = userService.join(user);

        //then
        Optional<User> findUser = userRepository.findById(savedId);
        assertTrue(findUser.isPresent());
        assertEquals(user, findUser.get());
    }
    
    @Test
    public void 중복_아이디_예외() throws Exception {
        //given
        
        //when
        
        //then
    }

}