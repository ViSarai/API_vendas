package com.example.handler.errorsapi;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    @Getter
    private final List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String msgError) {
        this.errors = Arrays.asList(msgError);
    }
}
