package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.*;
import com.example.demo.services.DemoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DemoController {
    private final DemoService service;

    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

    @GetMapping("/return-error")
    public String getError(){
        return "error";
    }

    @GetMapping("/controller-throw")
    public String getControllerThrow() throws Exception{
        throw new Exception("exception from getControllerThrow");
    }

    @GetMapping("service-throw")
    public String getServiceThrow() throws Exception{
        return service.getServiceThrow();
    }

    @GetMapping("repository-throw")
    public String getRepositoryThrow() throws Exception{
        return service.getRepositoryThrow();
    }

    @GetMapping("/controller-catch-throw")
    public String getControllerCatchThrow() throws Exception{
        try {
            throw new Exception("exception from getControllerCatchThrow");
        } catch (Exception e) {
            e.printStackTrace();
            return "controller-catch-throw";
        }
    }

    @GetMapping("service-catch-throw")
    public String getServiceCatchThrow() throws Exception{
        try {
            return service.getServiceThrow();
        } catch (Exception e) {
            e.printStackTrace();
            return "service-catch-throw";
        }

    }

    @GetMapping("repository-catch-throw")
    public String getRepositoryCatchThrow() throws Exception{
        try {
            return service.getRepositoryThrow();
        } catch (Exception e) {
            e.printStackTrace();
            return "repository-catch-throw";
        }
    }

    @GetMapping("validation-service-catch-throw")
    public ResponseEntity<String> getValidationServiceCatchThrow(
        @RequestParam(value = "name", required = true) String name
    ) throws Exception{
        try {
            var result = service.getValidationServiceThrow(name);
            return ResponseEntity.ok(result);
        } catch (ValidationException e){
            log.error(e.getValue());
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("unhandled-exception-service-catch-throw")
    public ResponseEntity<String> getUnhandledExceptionServiceCatchThrow() throws Exception{
        try {
            var result = service.getUnhandledExceptionServiceCatchThrow();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
