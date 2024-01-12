package com.stefandragomiroiu.rideshare.config.security;

import com.stefandragomiroiu.rideshare.enums.Role;
import com.stefandragomiroiu.rideshare.service.AuthService;
import com.stefandragomiroiu.rideshare.tables.pojos.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static org.jooq.tools.StringUtils.isEmpty;

@Component
public class AuthFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private final AuthService authService;

    public AuthFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Optional<Role> role = ProtectedEndpointsConfig.matches(request.getMethod(), request.getRequestURI());
        if (role.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            logger.error("Authentication failed for {} {} endpoint : JWT missing.", request.getMethod(), request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401
            return;
        }

        // Validate jwt token and retrieve user information
        final String encodedJWT = header.split(" ")[1].trim();
        User user;
        try {
            user = authService.verify(encodedJWT);
        } catch (Exception e) {
            logger.error("Authentication failed for {} {} endpoint : Invalid JWT. ", request.getMethod(), request.getRequestURI(), e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401
            return;
        }

        // Check if currently authenticated user has the permission/role to access this request's /URI
        if (!user.getRole().equals(role.get())) {
            logger.error("User {} does not have permission for {} {} endpoint. ",user.getUserId() , request.getMethod(), request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 403
            return;
        }

        request.setAttribute("user", user);
        chain.doFilter(request, response);
    }
}
