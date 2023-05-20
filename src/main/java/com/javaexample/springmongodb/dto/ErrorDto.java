package com.javaexample.springmongodb.dto;

import java.util.Objects;

public record ErrorDto(String code, String message) {
    public ErrorDto {
        Objects.requireNonNull(code);
        Objects.requireNonNull(message);
    }
}
