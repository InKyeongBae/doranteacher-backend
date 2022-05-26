package org.triathlongirls.doranssam.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("handleAccessDeniedException: " + accessDeniedException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().println(
                "{ \"status\" {"
                        + ", \"code\" : \"" + DoranssamErrorCode.ACCESS_DENIED.getCode()
                        + "{ \"message\" : \"" + DoranssamErrorCode.ACCESS_DENIED.getMessage()
                        + "\"},"
                        + "\"results\" : " + null
                        + "}");
    }
}
