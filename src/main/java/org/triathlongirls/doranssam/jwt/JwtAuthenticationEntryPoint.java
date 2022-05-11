package org.triathlongirls.doranssam.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        DoranssamErrorCode code = DoranssamErrorCode.ACCESS_DENIED;
        log.warn("handleAuthenticationException: " + code.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(
                "{ \"message\" : \"" + code.getMessage()
                        + "\", \"status\" : " + code.getStatus()
                        + ", \"errors\" : []"
                        + ", \"code\" : \"" + code.getCode()
                        + "\" }");

    }
}
