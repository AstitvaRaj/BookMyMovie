package com.bookmymovie.securityservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtility jwtUtility;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("authorization");
        if(request.getServletPath().startsWith("/auth/")){
            filterChain.doFilter(request, response);
            return;
        }
        boolean tokenVerified = jwtUtility.verifyToken(authorization);
        if(tokenVerified){
            UUID username = jwtUtility.getUsername(authorization);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username.toString(), "", jwtUtility.getAuthorities(authorization));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

}