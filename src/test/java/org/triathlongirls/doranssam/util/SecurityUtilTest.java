package org.triathlongirls.doranssam.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilTest {

    @Test
    void getCurrentUsername() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("doranssam", "doranssam"));
        SecurityContextHolder.setContext(securityContext);

        String username = SecurityUtil.getCurrentUsername();

        assertThat(username).contains("doranssam");
    }

    @Test
    public void getCurrentUsernameForNoAuthenticationInContext() {
        String username = SecurityUtil.getCurrentUsername();

        assertThat(username).isEmpty();
    }
}