package org.triathlongirls.doranssam.exception;

public class DoranssamException extends RuntimeException {

    private DoranssamErrorCode doranssamErrorCode;

    public DoranssamException(String message, DoranssamErrorCode doranssamErrorCode) {
        super(message);
        this.doranssamErrorCode = doranssamErrorCode;
    }

    public DoranssamException(DoranssamErrorCode doranssamErrorCode) {
        super(doranssamErrorCode.getMessage());
        this.doranssamErrorCode = doranssamErrorCode;
    }

    public DoranssamErrorCode getErrorCode() {
        return doranssamErrorCode;
    }
}
