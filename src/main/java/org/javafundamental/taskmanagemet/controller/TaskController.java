package org.javafundamental.taskmanagemet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.javafundamental.taskmanagemet.dto.CreateTaskRequest;
import org.javafundamental.taskmanagemet.dto.TaskResponse;
import org.javafundamental.taskmanagemet.dto.TaskStatResponse;
import org.javafundamental.taskmanagemet.dto.UpdateStatusRequest;
import org.javafundamental.taskmanagemet.dto.UpdateTaskRequest;
import org.javafundamental.taskmanagemet.entity.User;
import org.javafundamental.taskmanagemet.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // public TaskController(TaskService taskService) {
    // this.taskService = taskService;
    // }

    // GET /api/tasks
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(taskService.getAllTasks(currentUser));
    }

    @GetMapping("/stats")
    public ResponseEntity<TaskStatResponse> getTaskStats(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(taskService.getTaskStats(currentUser));
    }

    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(taskService.getTaskById(id, currentUser));
    }

    // POST /api/tasks
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest request,
            @AuthenticationPrincipal User currentUser) {
        TaskResponse response = taskService.createTask(request, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request,
            @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(taskService.updateTask(id, request, currentUser));
    }

    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        taskService.deleteTask(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request, @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, request, currentUser));
    }

}
