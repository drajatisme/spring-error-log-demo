package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {
    public String getRepositoryThrow() throws Exception {
        throw new Exception("exception from getRepositoryThrow");
    }
}
