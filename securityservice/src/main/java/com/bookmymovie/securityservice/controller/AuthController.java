package com.bookmymovie.securityservice.controller;

import com.bookmymovie.securityservice.dto.request.CreateUserRequest;
import com.bookmymovie.securityservice.dto.request.LoginRequest;
import com.bookmymovie.securityservice.service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginDetails){
        return authServices.login(loginDetails);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody CreateUserRequest createUserRequest){
        return authServices.createUser(createUserRequest);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> loginDetails){
        return authServices.resetPassword(loginDetails);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(@CookieValue(name = "refreshToken") String refreshToken){
        return authServices.getRefreshToken(refreshToken);
    }

}
