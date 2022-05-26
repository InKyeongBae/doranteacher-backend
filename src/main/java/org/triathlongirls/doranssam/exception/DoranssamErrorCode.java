package org.triathlongirls.doranssam.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.servlet.http.HttpServletResponse;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DoranssamErrorCode {

    // Common
    BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, 4000, "Bad Request"),
    INVALID_INPUT_VALUE(HttpServletResponse.SC_BAD_REQUEST, 4001, "Invalid Input Value"),
    ENTITY_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, 4002, "Entity Not Found"),
    INVALID_TYPE_VALUE(HttpServletResponse.SC_BAD_REQUEST, 4003, "Invalid Type Value"),
    ACCESS_DENIED(HttpServletResponse.SC_BAD_REQUEST, 4004, "Access is Denied"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, 4005,"Not Found"),
    METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, 4006, "Method Not Allowed"),
    NOT_ACCEPTABLE(HttpServletResponse.SC_NOT_ACCEPTABLE, 4007, "Not Acceptable"),
    UNSUPPORTED_MEDIA_TYPE(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,4008, "Unsupported Media Type"),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, 4009, "Unauthorized"),

    BAD_CREDENTIAL(HttpServletResponse.SC_UNAUTHORIZED, 4010, "Bad Credential"),
    WRONG_TYPE_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, 4011, "Invalid JWT Signature"),
    UNSUPPORTED_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, 4012, "Unsupported Token"),
    EXPIRED_TOKEN(HttpServletResponse.SC_UNAUTHORIZED,4013, "Expired JWT Token"),
    WRONG_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, 4014, "Invalid JWT Token"),
    BLACKLISTED_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, 4014, "Invalid JWT Token"),

    // Member
    USERNAME_DUPLICATION(HttpServletResponse.SC_BAD_REQUEST, 4101, "이미 가입한 사용자입니다."),
    LOGIN_INPUT_INVALID(HttpServletResponse.SC_BAD_REQUEST, 4102, "아이디 또는 비밀번호가 잘못되었습니다."),
    PASSWORD_NOT_SAME(HttpServletResponse.SC_BAD_REQUEST, 4102, "비밀번호가 일치하지 않습니다."),


    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 5001, "Server Error"),
    SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, 5002, "Service Unavailable"),

    ;

    private final Integer code;
    private final String message;
    private Integer status;

    DoranssamErrorCode(final int status, final Integer code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
