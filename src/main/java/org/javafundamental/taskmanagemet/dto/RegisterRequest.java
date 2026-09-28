package org.javafundamental.taskmanagemet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Username tidak boleh kosong") @Size(min = 3, max = 50, message = "Username minimal 3 karakter") String username,
        @NotBlank(message = "Email tidak boleh kosong") @Email(message = "Format email tidak valid") String email,
        @NotBlank(message = "Password tidak boleh kosong") @Size(min = 6, message = "Password minimal 6 karakter") String password) {
}