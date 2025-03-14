package com.devDream.coDream.exceptions;

public class coDreamException extends RuntimeException{

    public coDreamException(String message) {
        super(message);
    }

    public coDreamException(String message, Exception e) {
        super(message, e);
    }
}
