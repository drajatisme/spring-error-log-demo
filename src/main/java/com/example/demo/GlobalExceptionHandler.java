package com.example.demo;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        saveLog(e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        saveLog(e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    private void saveLog(Exception e){
        try {
            e.printStackTrace();

            var uriComponents = ServletUriComponentsBuilder.fromCurrentRequestUri().build();

            var log = new ErrorLogModel();
            log.setMessage(e.getMessage());
            log.setPayload(null);
            log.setStacktrace(Arrays.toString(e.getStackTrace()));
            log.setUrl(uriComponents.getScheme() + "://" + uriComponents.getHost());
            log.setPath(uriComponents.getPath());

            errorLogRepository.save(log);

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
