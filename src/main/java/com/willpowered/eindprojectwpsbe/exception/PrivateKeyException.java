package com.willpowered.eindprojectwpsbe.exception;


public class PrivateKeyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PrivateKeyException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public PrivateKeyException(String message) {
        super(message);
    }
}
