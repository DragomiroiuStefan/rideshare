package com.stefandragomiroiu.rideshare.config.security;

public record UsernamePasswordToken(
        String username,
        String password
) {
}
