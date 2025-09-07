package com.bookmymovie.events.security;

import com.bookmymovie.events.utility.KeyValue;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class JwtUtility {

    @Autowired
    KeyValue keyValue;

    public boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(keyValue.getJwtSecret())).build();
            DecodedJWT verify = verifier.verify(token);
            return true;
        } catch (JWTVerificationException jwtVerificationException) {
            jwtVerificationException.printStackTrace();
            return false;
        }
    }

    public UUID getUsername(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(keyValue.getJwtSecret())).build();
        DecodedJWT verify = verifier.verify(token);
        return UUID.fromString(verify.getClaims().get("username").asString());
    }

    public List<SimpleGrantedAuthority> getAuthorities(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(keyValue.getJwtSecret())).build();
        DecodedJWT verify = verifier.verify(token);
        return Arrays.stream(verify.getClaim("role").asString().split(",")).map(SimpleGrantedAuthority::new).toList();
    }

}
