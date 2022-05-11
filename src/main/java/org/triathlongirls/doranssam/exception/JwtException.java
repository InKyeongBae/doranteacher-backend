package org.triathlongirls.doranssam.exception;

public class JwtException extends  DoranssamException{

    public JwtException(String value) {
        super(value, DoranssamErrorCode.UNAUTHORIZED);
    }

    public JwtException(String value, DoranssamErrorCode doranssamErrorCode) {
        super(value, doranssamErrorCode);
    }
}
