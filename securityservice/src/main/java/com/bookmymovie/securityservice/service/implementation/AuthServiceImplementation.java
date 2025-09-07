package com.bookmymovie.securityservice.service.implementation;

import com.bookmymovie.securityservice.dto.request.CreateUserRequest;
import com.bookmymovie.securityservice.dto.request.LoginRequest;
import com.bookmymovie.securityservice.entity.Users;
import com.bookmymovie.securityservice.repository.UserTypeEntityRepository;
import com.bookmymovie.securityservice.repository.UsersRepository;
import com.bookmymovie.securityservice.security.JwtUtility;
import com.bookmymovie.securityservice.service.AuthServices;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImplementation implements AuthServices {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserTypeEntityRepository userTypeEntityRepository;

    @Override
    public ResponseEntity<?> login(LoginRequest loginDetails) {
        try {
            Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getEmail(), loginDetails.getPassword()));
            Optional<String> reduce = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).reduce((o1, o2) -> o1 + "," + o2);
            String token = jwtUtility.generateToken(authenticate.getName(), reduce.orElse(""));
            String refreshToken = jwtUtility.generateRefreshToken(authenticate.getName(), reduce.orElse(""));
            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken).httpOnly(true).secure(false).sameSite("Strict").path("/auth/refresh").maxAge(Duration.ofDays(7)).build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Map.of("access-token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> createUser(CreateUserRequest createUserRequest) {
        try {
            Users users = createUserRequest.generateUsers(encoder, userTypeEntityRepository);
            usersRepository.saveAndFlush(users);
            Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(createUserRequest.getEmail(), createUserRequest.getPassword()));
            Optional<String> reduce = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).reduce((o1, o2) -> o1 + "," + o2);
            String token = jwtUtility.generateToken(authenticate.getName(), reduce.orElse(""));
            String refreshToken = jwtUtility.generateRefreshToken(authenticate.getName(), reduce.orElse(""));
            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken).httpOnly(true).secure(false).sameSite("Strict").path("/auth/refresh").maxAge(Duration.ofDays(7)).build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Map.of("access-token", token));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + ":::" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> resetPassword(Map<String, String> loginDetails) {
        Optional<Users> username = usersRepository.findByEmail(loginDetails.get("username"));
        if (username.isPresent()) {
            Users users = username.get();
            users.setPassword(encoder.encode(loginDetails.get("password")));
            usersRepository.save(users);
            Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.get("username"), loginDetails.get("password")));
            Optional<String> reduce = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).reduce((o1, o2) -> o1 + "," + o2);
            String token = jwtUtility.generateToken(authenticate.getName(), reduce.orElse(""));
            String refreshToken = jwtUtility.generateRefreshToken(authenticate.getName(), reduce.orElse(""));
            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken).httpOnly(true).secure(false).sameSite("Strict").path("/auth/refresh").maxAge(Duration.ofDays(7)).build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Map.of("access-token", token));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Username");
    }

    @Override
    public ResponseEntity<?> getRefreshToken(String refreshToken) {
        if (jwtUtility.verifyToken(refreshToken)) {
            DecodedJWT decode = JWT.decode(refreshToken);
            Claim username = decode.getClaim("username");
            Claim roles = decode.getClaim("role");
            String token = jwtUtility.generateToken(username.asString(), roles.asString());
            String newRefreshToken = jwtUtility.generateRefreshToken(username.asString(), roles.asString());
            ResponseCookie cookie = ResponseCookie.from("refreshToken", newRefreshToken).httpOnly(true).secure(false).sameSite("Strict").path("/auth/refresh").maxAge(Duration.ofDays(7)).build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Map.of("access-token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("msg", "Refresh token Expired"));
    }
}
