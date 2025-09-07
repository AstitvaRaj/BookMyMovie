package com.bookmymovie.securityservice.service;

import com.bookmymovie.securityservice.dto.request.CreateUserRequest;
import com.bookmymovie.securityservice.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthServices {

    ResponseEntity<?> login(LoginRequest loginDetails);

    ResponseEntity<?> createUser(CreateUserRequest createUserRequest);

    ResponseEntity<?> resetPassword(Map<String, String> loginDetails);

    ResponseEntity<?> getRefreshToken(String authorization);

}
