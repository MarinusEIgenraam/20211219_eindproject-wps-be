package com.willpowered.eindprojectwpsbe.Exception;


import java.io.Serializable;

public class FileStorageException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String msg;

    public FileStorageException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}