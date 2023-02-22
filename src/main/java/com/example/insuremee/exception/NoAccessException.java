package com.example.insuremee.exception;

public class NoAccessException extends Exception{

    public NoAccessException() { super();}

    public NoAccessException(String message) {
        super(message);
    }
}
