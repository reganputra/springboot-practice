package org.javafundamental.taskmanagemet.dto;

import java.time.LocalDateTime;

import org.javafundamental.taskmanagemet.entity.TaskPriority;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
        @NotBlank(message = "Title tidak boleh kosong") @Size(min = 3, max = 100, message = "Title harus antara 3 - 100 karakter") String title,
        @Size(max = 500, message = "Description maksimal 500 karakter") String description,
        TaskPriority priority,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dueDate) {
}
