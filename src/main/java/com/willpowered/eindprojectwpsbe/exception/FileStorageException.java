package com.willpowered.eindprojectwpsbe.exception;


import java.io.Serializable;

public class FileStorageException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg;

    public FileStorageException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}