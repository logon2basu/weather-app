package com.weatherapp.exception;

/**
 * Custom Exception
 */
public class NoCityException extends RuntimeException {
    private String message;

    public NoCityException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "NoCityException{" +
                "message='" + message + '\'' +
                '}';
    }
}
