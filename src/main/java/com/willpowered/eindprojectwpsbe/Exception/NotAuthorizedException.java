package com.willpowered.eindprojectwpsbe.Exception;


import java.io.Serializable;

public class NotAuthorizedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public NotAuthorizedException(String message) {
        super(message);
    }
    public NotAuthorizedException() {
        super("Not authorized.");
    }
}