package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {
    public String getRepositoryThrow() throws Exception {
        throw new Exception("exception from getRepositoryThrow");
    }
}
