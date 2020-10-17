package com.mondocto.ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAleradyExistsException extends RuntimeException {
    private String message;
    public ResourceAleradyExistsException(String message) {
        super(message);
    }
}
