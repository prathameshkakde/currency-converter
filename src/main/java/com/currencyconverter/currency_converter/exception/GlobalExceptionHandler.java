package com.currencyconverter.currency_converter.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntimeException(RuntimeException ex){

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        return errorResponse;
    }
}
