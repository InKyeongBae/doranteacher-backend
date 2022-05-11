package org.triathlongirls.doranssam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.triathlongirls.doranssam.dto.ErrorResponse;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;


@Slf4j
@RestControllerAdvice
public class DoranssamExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e, WebRequest request) {
        log.warn("handleHttpRequestMethodNotSupportedException: " + e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> supportedMethods = e.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e, WebRequest request) {
        log.warn("handleHttpMediaTypeNotSupportedException: " + e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypes = e.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }

        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.UNSUPPORTED_MEDIA_TYPE);
        return new ResponseEntity<>(response, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptableException(
            HttpMediaTypeNotAcceptableException e, WebRequest request) {
        log.warn("handleHttpMediaTypeNotAcceptableException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.NOT_ACCEPTABLE);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    protected ResponseEntity<ErrorResponse> handleMissingPathVariableException(
            MissingPathVariableException e, WebRequest request) {
        log.warn("handleMissingPathVariableException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e, WebRequest request) {
        log.warn("handleMissingServletRequestParameterException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    protected ResponseEntity<ErrorResponse> handleServletRequestBindingException(
            ServletRequestBindingException e, WebRequest request) {
        log.warn("handleServletRequestBindingException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleConversionNotSupportedException(
            ConversionNotSupportedException e, WebRequest request) {
        log.warn("handleConversionNotSupportedException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e, WebRequest request) {
        log.warn("handleMethodArgumentTypeMismatchException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e, WebRequest request) {
        log.warn("handleHttpMessageNotReadableException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotWritableException(
            HttpMessageNotWritableException e, WebRequest request) {
        log.warn("handleHttpMessageNotWritableException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, WebRequest request) {
        log.warn("handleMethodArgumentNotValidException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    protected ResponseEntity<ErrorResponse> handleMissingServletRequestPartException(
            MissingServletRequestPartException e, WebRequest request) {
        log.warn("handleMissingServletRequestPartException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(
            BindException e, WebRequest request) {
        log.warn("handleBindException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
            NoHandlerFoundException e, WebRequest request) {
        log.warn("handleNoHandlerFoundException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    protected ResponseEntity<ErrorResponse> handleAsyncRequestTimeoutException(
            AsyncRequestTimeoutException e, WebRequest request) {
        log.warn("handleAsyncRequestTimeoutException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException e, WebRequest request) {
        log.warn("handleAccessDeniedException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(DoranssamErrorCode.ACCESS_DENIED.getStatus()));
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    protected ResponseEntity<ErrorResponse> handleUnauthorized(
            AccessDeniedException e, WebRequest request) {
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DoranssamException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(
            final DoranssamException e, WebRequest request) {
        log.warn("handleEntityNotFoundException", e);
        final DoranssamErrorCode doranssamErrorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(doranssamErrorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(doranssamErrorCode.getStatus()));
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException e, WebRequest request) {
        log.warn("handleAuthenticationException: " + e.getMessage());
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(
            Exception e, WebRequest request) {
        log.error("handleException: ", e);
        final ErrorResponse response = ErrorResponse.of(DoranssamErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
