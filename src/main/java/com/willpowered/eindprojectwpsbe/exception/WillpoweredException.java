package com.willpowered.eindprojectwpsbe.exception;

import java.io.Serializable;

public class WillpoweredException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public WillpoweredException(String exMessage) {
        super(exMessage);
    }
}
