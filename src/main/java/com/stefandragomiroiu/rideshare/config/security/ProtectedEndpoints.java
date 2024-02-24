package com.stefandragomiroiu.rideshare.config.security;

import com.stefandragomiroiu.rideshare.jooq.enums.Role;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Class containing protected endpoints that require authentication and/or authorization
 */
public class ProtectedEndpoints {

    private ProtectedEndpoints() {}

    private static final Map<String, Role> endpoints = Map.of(
            "POST /rides", Role.USER,
            "POST /users", Role.USER,
            "PUT /users/\\d+", Role.USER,
            "DELETE /users/\\d+", Role.USER
            );

    public static Optional<Role> matches(String method, String url) {
        for (Map.Entry<String, Role> entry : endpoints.entrySet()) {
            if (Pattern.matches(entry.getKey(), method + " " + url)) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

}
