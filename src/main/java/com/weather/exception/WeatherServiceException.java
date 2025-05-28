package com.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

public class WeatherServiceException extends RuntimeException {
    /**
     * Custom exception for weather service errors.
     * Thrown when an error occurs while fetching or processing weather data.
     */
    public WeatherServiceException(String message) {
        super(message);
    }
}