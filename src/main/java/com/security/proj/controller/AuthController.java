package com.security.proj.controller;

import com.security.proj.exception.AuthException;
import com.security.proj.security.JwtRequest;
import com.security.proj.security.JwtResponse;
import com.security.proj.security.RefreshJwtRequest;
import com.security.proj.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody JwtRequest authRequest) {
        try {
            final JwtResponse token = authService.login(authRequest);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("token")
    public ResponseEntity<Object> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        try {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("refresh")
    public ResponseEntity<Object> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        try {
            final JwtResponse token = authService.refresh(request.getRefreshToken());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}