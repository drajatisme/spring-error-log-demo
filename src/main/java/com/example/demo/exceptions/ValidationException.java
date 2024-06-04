package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends Exception {
    private String value;

    public ValidationException(String message, String value) {
        super(message);
        this.value = value;
    }
}
