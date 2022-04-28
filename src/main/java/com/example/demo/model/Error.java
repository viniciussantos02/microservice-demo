package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
    private String userMessage;
    private String devMessage;
}
