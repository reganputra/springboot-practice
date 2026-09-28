package org.javafundamental.taskmanagemet.service;

import org.javafundamental.taskmanagemet.dto.AuthResponse;
import org.javafundamental.taskmanagemet.dto.LoginRequest;
import org.javafundamental.taskmanagemet.dto.RegisterRequest;
import org.javafundamental.taskmanagemet.dto.RegisterResponse;
import org.javafundamental.taskmanagemet.repository.UserRepository;
import org.javafundamental.taskmanagemet.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.javafundamental.taskmanagemet.entity.User;
import org.javafundamental.taskmanagemet.exception.ResourceNotFoundException;
import org.javafundamental.taskmanagemet.entity.Role;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // public AuthService(UserRepository userRepository, PasswordEncoder
    // passwordEncoder, JwtService jwtService) {
    // this.userRepository = userRepository;
    // this.passwordEncoder = passwordEncoder;
    // this.jwtService = jwtService;
    // }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username sudah digunakan");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email sudah terdaftar");
        }
        // Hashing password dengan BCrypt sebelum disimpan ke database
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(
                request.username(),
                request.email(),
                encodedPassword,
                Role.USER);
        User savedUser = userRepository.save(user);
        return new RegisterResponse(savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        // Cari user berdasarkan username ATAU email
        User user = userRepository.findByUsername(request.usernameOrEmail())
                .or(() -> userRepository.findByEmail(request.usernameOrEmail()))
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan"));

        // Verifikasi kesesuaian password plain dengan hash di database
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("password salah");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getUsername(), user.getEmail(), user.getRole().name());
    }
}
