package com.willpowered.eindprojectwpsbe.exception;


import java.io.Serializable;

public class InvalidPasswordException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidPasswordException(String message) {
        super(message);
    }
    public InvalidPasswordException() {
        super("Invalid password.");
    }
}