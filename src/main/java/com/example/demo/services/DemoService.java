package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ValidationException;
import com.example.demo.repositories.DemoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final DemoRepository repository;

    public String getServiceThrow() throws Exception {
        throw new Exception("exception from getServiceThrow");
    }

    public String getValidationServiceThrow(String name) throws Exception{
        var nameLengthThresshold = 10;
        var nameLength = name.length();

        if (nameLength > nameLengthThresshold) {
            var message = String.format("Name length cannot exceed %d characters, current %d", nameLengthThresshold, nameLength);
            throw new ValidationException(message, name);
        }
        return "valid";
    }

    public String getRepositoryThrow() throws Exception {
        return repository.getRepositoryThrow();
    }

    public String getUnhandledExceptionServiceCatchThrow() throws Exception {
        var x1 = 5/5;
        var x2 = 5/4;
        var x3 = 5/3;
        var x4 = 5/2;
        var x5 = 5/1;
        var x6 = 5/0;
        
        return "result";
    }
}
