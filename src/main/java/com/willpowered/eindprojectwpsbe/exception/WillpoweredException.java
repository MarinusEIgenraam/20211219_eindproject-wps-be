package com.willpowered.eindprojectwpsbe.exception;

public class WillpoweredException extends RuntimeException {
    public WillpoweredException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public WillpoweredException(String exMessage) {
        super(exMessage);
    }
}
