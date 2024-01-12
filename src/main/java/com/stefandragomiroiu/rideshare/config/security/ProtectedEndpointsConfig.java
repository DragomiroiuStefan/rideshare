package com.stefandragomiroiu.rideshare.config.security;

import com.stefandragomiroiu.rideshare.enums.Role;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Class containing protected endpoints that require authentication and/or authorization
 */
public class ProtectedEndpointsConfig {

    private ProtectedEndpointsConfig() {}

    private static final Map<String, Role> protectedEndpoints = Map.of(
            "POST /rides", Role.USER,
            "POST /users", Role.ADMIN,
            "PUT /users/\\d+", Role.ADMIN,
            "DELETE /users/\\d+", Role.ADMIN
            );

    public static Optional<Role> matches(String method, String url) {
        for (String key : protectedEndpoints.keySet()) {
            if (Pattern.matches(key, method + " " + url)) {
                return Optional.of(protectedEndpoints.get(key));
            }
        }
        return Optional.empty();
    }

}
