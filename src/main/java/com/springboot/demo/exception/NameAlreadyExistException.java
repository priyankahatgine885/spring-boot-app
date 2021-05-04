package com.springboot.demo.exception;

public class NameAlreadyExistException extends RuntimeException{
    public NameAlreadyExistException(String exception) {
        super(exception);
    }
}
