package com.willpowered.eindprojectwpsbe.exception;


public class PublicKeyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PublicKeyException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public PublicKeyException(String message) {
        super(message);
    }
}
