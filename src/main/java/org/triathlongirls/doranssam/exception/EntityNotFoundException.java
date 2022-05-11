package org.triathlongirls.doranssam.exception;

public class EntityNotFoundException extends DoranssamException {

    public EntityNotFoundException(String message) {
        super(message, DoranssamErrorCode.ENTITY_NOT_FOUND);
    }
}
