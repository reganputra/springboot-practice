package org.javafundamental.taskmanagemet.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Username atau email tidak boleh kosong") String usernameOrEmail,
        @NotBlank(message = "Password tidak boleh kosong") String password) {
}
