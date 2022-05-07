package org.triathlongirls.doranssam.jwt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.repository.UserRepository;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.debug("Authenticating user '{}'", username);
        String lowercaseUsername = username.toLowerCase(Locale.ENGLISH);
        return userRepository.findByUsername(lowercaseUsername)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(lowercaseUsername + " 사용자를 찾을 수 없습니다."));
    }

    private CustomUserDetails createUserDetails(User member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());
        Set<GrantedAuthority> authorities = Collections.singleton(grantedAuthority);

        return CustomUserDetails.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }
}
