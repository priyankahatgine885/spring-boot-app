package com.springboot.demo.exception;

public class EmptyListException extends RuntimeException{
    public EmptyListException(String exception) {
        super(exception);
    }
}
