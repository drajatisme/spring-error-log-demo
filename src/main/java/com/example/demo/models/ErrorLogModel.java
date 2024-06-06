package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "error_log")
@Getter
@Setter
public class ErrorLogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "path")
    private String path;

    @Column(name = "payload")
    private String payload;

    @Column(name = "message")
    private String message;

    @Column(name = "stacktrace", columnDefinition="text")
    private String stacktrace;

}
