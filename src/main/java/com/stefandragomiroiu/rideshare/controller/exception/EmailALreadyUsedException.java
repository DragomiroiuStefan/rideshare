package com.stefandragomiroiu.rideshare.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailALreadyUsedException extends RuntimeException {

    public EmailALreadyUsedException() {
    }

    public EmailALreadyUsedException(String message) {
        super(message);
    }
}
