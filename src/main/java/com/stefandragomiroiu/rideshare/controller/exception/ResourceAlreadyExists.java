package com.stefandragomiroiu.rideshare.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists() {
    }

    public ResourceAlreadyExists(String message) {
        super(message);
    }
}
