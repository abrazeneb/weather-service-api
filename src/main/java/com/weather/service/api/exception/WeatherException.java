package com.weather.service.api.exception;

public class WeatherException extends RuntimeException {

    public WeatherException(final String errorCause) {
        super(errorCause);
    }
}
