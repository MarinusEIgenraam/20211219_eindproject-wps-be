package com.willpowered.eindprojectwpsbe.exception;


import java.io.Serializable;

public class UserNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String username) {
        super("Cannot find user " + username);
    }
    public UserNotFoundException() {
        super("User not found.");
    }

}
