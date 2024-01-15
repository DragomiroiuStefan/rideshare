package com.stefandragomiroiu.rideshare.service;

import com.stefandragomiroiu.rideshare.controller.UserController;
import com.stefandragomiroiu.rideshare.controller.exception.InvalidCredentialsException;
import com.stefandragomiroiu.rideshare.jooq.enums.Role;
import com.stefandragomiroiu.rideshare.jooq.tables.pojos.User;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class AuthService {

    private final String jwtSecretKey = "4BqHPHxribtwRxqjWcJU8wYQ7za86CSH4jmngPPjifFrBc4NXiXXSJgLaiCrL8EtBExFjevg5BRfhzCdaSTXeKD8bFdz6zaJzWz4uNvcLTZT6mE9hrmd4CUQxkvA5jjeCtqunHxJTzQxEr7tJ8x2dt";

    public String createJWT(User user) {
        // Build an HMAC signer using an SHA-256 hash
        Signer signer = HMACSigner.newSHA256Signer(jwtSecretKey);

        // Build a new JWT with an issuer(iss), issued at(iat), subject(sub) and expiration(exp)
        JWT jwt = new JWT()
                .setIssuer("www.rideshare.pages.dev")
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(user.getUserId().toString())
                .addClaim("email", user.getEmail())
                .addClaim("role", user.getRole());

        // Sign and encode the JWT to a JSON string representation
        return JWT.getEncoder().encode(jwt, signer);
    }

    public User verify(String encodedJWT) {
        // Build an HMC verifier using the same secret that was used to sign the JWT
        Verifier verifier = HMACVerifier.newVerifier(jwtSecretKey);

        // Verify and decode the encoded string JWT to a rich object
        JWT jwt = JWT.getDecoder().decode(encodedJWT, verifier);

        var user = new User();
        user.setUserId(Long.parseLong(jwt.subject));
        user.setEmail(jwt.getOtherClaims().get("email").toString());
        user.setRole(Role.valueOf(jwt.getOtherClaims().get("role").toString()));

        return user;
    }

}
