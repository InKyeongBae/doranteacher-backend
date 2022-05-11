package org.triathlongirls.doranssam.exception;

public class InvalidValueException extends DoranssamException {

    public InvalidValueException(String value) {
        super(value, DoranssamErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, DoranssamErrorCode doranssamErrorCode) {
        super(value, doranssamErrorCode);
    }
}
