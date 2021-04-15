package com.weatherapp.exception;

/**
 * Custom Exception
 */
public class CityNotFoundException extends RuntimeException {

    private  final String message;

    public CityNotFoundException(String s) {
        this.message = s;

    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CityNotFoundExcpetion{" +
                "message='" + message + '\'' +
                '}';
    }
}
