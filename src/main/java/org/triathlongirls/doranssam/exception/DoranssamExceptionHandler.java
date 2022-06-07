package org.triathlongirls.doranssam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.triathlongirls.doranssam.dto.ApiResponse;


@Slf4j
@RestControllerAdvice
public class DoranssamExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        ApiResponse response = new ApiResponse<>().fail(status.value(), ex.getMessage());
        return super.handleExceptionInternal(ex, response, headers, status, request);
    }


    @ExceptionHandler(DoranssamException.class)
    protected ResponseEntity<ApiResponse> handleBusinessException(
            final DoranssamException e, WebRequest request) {
        log.warn("handleEntityNotFoundException", e);
        final DoranssamErrorCode doranssamErrorCode = e.getErrorCode();
        ApiResponse<Object> response = new ApiResponse<>().fail(doranssamErrorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(doranssamErrorCode.getStatus()));
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ApiResponse> handleAuthenticationException(
            AuthenticationException e, WebRequest request) {
        log.warn("handleAuthenticationException: " + e.getMessage());
        ApiResponse<Object> response = new ApiResponse<>().fail(DoranssamErrorCode.UNAUTHORIZED.getCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<ApiResponse> handleException(
            Throwable t, WebRequest request) {
        log.error("handleException: ", t);
        ApiResponse<Object> response = new ApiResponse<>().fail(DoranssamErrorCode.INTERNAL_SERVER_ERROR.getCode(), t.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
