package org.triathlongirls.doranssam.jwt;

public interface JwtProperties {
    String AUTHORITIES_KEY = "auth";
    long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 7 * 1000L;           // 7days
    long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 10L;  // 10days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}