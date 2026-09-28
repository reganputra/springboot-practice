package org.javafundamental.taskmanagemet.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> validationErrors,
        LocalDateTime timestamp) {
    // Constructor pembantu jika tidak ada validation errors
    public ErrorResponse(int status, String error, String message) {
        this(status, error, message, null, LocalDateTime.now());
    }

    // Constructor pembantu jika ada validation errors
    public ErrorResponse(int status, String error, String message, Map<String, String> validationErrors) {
        this(status, error, message, validationErrors, LocalDateTime.now());
    }
}