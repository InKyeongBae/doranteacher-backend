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
        ApiResponse response = new ApiResponse<>().fail(DoranssamErrorCode.BAD_REQUEST.getCode());
        //ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INVALID_INPUT_VALUE, ex.getMessage());
        return super.handleExceptionInternal(ex, response, headers, status, request);
    }


    @ExceptionHandler(DoranssamException.class)
    protected ApiResponse<Object> handleBusinessException(
            final DoranssamException e, WebRequest request) {
        log.warn("handleEntityNotFoundException", e);
        final DoranssamErrorCode doranssamErrorCode = e.getErrorCode();
        return new ApiResponse<>().fail(doranssamErrorCode.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ApiResponse<Object> handleAuthenticationException(
            AuthenticationException e, WebRequest request) {
        log.warn("handleAuthenticationException: " + e.getMessage());
        return new ApiResponse<>().fail(DoranssamErrorCode.UNAUTHORIZED.getCode(), e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    protected ApiResponse<Object> handleException(
            Throwable t, WebRequest request) {
        log.error("handleException: ", t);
        return new ApiResponse<>().fail(DoranssamErrorCode.INTERNAL_SERVER_ERROR.getCode(), t.getMessage());
    }
}
