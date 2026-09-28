package org.javafundamental.taskmanagemet.dto;

public record RegisterResponse(
        String username,
        String email,
        String role) {
}