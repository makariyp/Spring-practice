package com.example.demo.exception;

public class NoSuchAuthorException extends RuntimeException {
    public NoSuchAuthorException(String message) {
        super(message);
    }
}
