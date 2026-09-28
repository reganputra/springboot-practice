package org.javafundamental.taskmanagemet.controller;

import org.javafundamental.taskmanagemet.dto.AuthResponse;
import org.javafundamental.taskmanagemet.dto.LoginRequest;
import org.javafundamental.taskmanagemet.dto.RegisterRequest;
import org.javafundamental.taskmanagemet.dto.RegisterResponse;
import org.javafundamental.taskmanagemet.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // public AuthController(AuthService authService) {
    // this.authService = authService;
    // }

    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
