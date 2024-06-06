package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exceptions.*;
import com.example.demo.models.ErrorLogModel;
import com.example.demo.repositories.ErrorLogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorLogRepository errorLogRepository;

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        e.printStackTrace();
        log.error(e.getValue());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        var log = new ErrorLogModel();
        log.setRequestData(e.getMessage());
        errorLogRepository.save(log);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
