package org.javafundamental.taskmanagemet.dto;

import java.time.LocalDateTime;

import org.javafundamental.taskmanagemet.entity.Task;
import org.javafundamental.taskmanagemet.entity.TaskPriority;
import org.javafundamental.taskmanagemet.entity.TaskStatus;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long userId,
        String username) {
    // Factory method untuk konversi Entity ke DTO
    public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUser().getId(),
                task.getUser().getUsername());
    }
}
