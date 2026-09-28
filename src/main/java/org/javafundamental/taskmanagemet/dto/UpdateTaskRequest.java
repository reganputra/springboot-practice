package org.javafundamental.taskmanagemet.dto;

import java.time.LocalDateTime;

import org.javafundamental.taskmanagemet.entity.TaskPriority;
import org.javafundamental.taskmanagemet.entity.TaskStatus;

public record UpdateTaskRequest(
                String title,
                String description,
                TaskPriority priority,
                LocalDateTime dueDate,
                TaskStatus status) {
}
