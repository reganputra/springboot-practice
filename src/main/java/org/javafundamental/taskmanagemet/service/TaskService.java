package org.javafundamental.taskmanagemet.service;

import org.javafundamental.taskmanagemet.dto.CreateTaskRequest;
import org.javafundamental.taskmanagemet.dto.TaskResponse;
import org.javafundamental.taskmanagemet.dto.TaskStatResponse;
import org.javafundamental.taskmanagemet.dto.UpdateStatusRequest;
import org.javafundamental.taskmanagemet.dto.UpdateTaskRequest;
import org.javafundamental.taskmanagemet.entity.Role;
import org.javafundamental.taskmanagemet.entity.Task;
import org.javafundamental.taskmanagemet.entity.TaskPriority;
import org.javafundamental.taskmanagemet.entity.TaskStatus;
import org.javafundamental.taskmanagemet.entity.User;
import org.javafundamental.taskmanagemet.exception.ResourceNotFoundException;
import org.javafundamental.taskmanagemet.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    // Constructor Injection (Best Practice Dependency Injection)
    // public TaskService(TaskRepository taskRepository) {
    // this.taskRepository = taskRepository;
    // }

    // 1. Get All: Admin melihat semua, User biasa hanya melihat miliknya
    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks(User currentUser) {
        List<Task> tasks;
        if (currentUser.getRole() == Role.ADMIN) {
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findByUserId(currentUser.getId());
        }
        return tasks.stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }

    // 2. Get by ID: Cek apakah task milik currentUser
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id, User currentUser) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task tidak ditemukan dengan id: " + id));
        validateOwnership(task, currentUser);
        return TaskResponse.fromEntity(task);
    }

    // 3. Create Task: Otomatis menautkan currentUser
    @Transactional
    public TaskResponse createTask(CreateTaskRequest request, User currentUser) {
        Task task = new Task(request.title(), request.description(), TaskStatus.TODO, currentUser);
        TaskPriority priority = request.priority() != null ? request.priority() : TaskPriority.MEDIUM;
        task.setPriority(priority);
        task.setDueDate(request.dueDate());
        Task savedTask = taskRepository.save(task);
        return TaskResponse.fromEntity(savedTask);
    }

    // 4. Update Task: Cek izin sebelum update
    @Transactional
    public TaskResponse updateTask(Long id, UpdateTaskRequest request, User currentUser) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task tidak ditemukan dengan id: " + id));
        validateOwnership(task, currentUser);
        if (request.title() != null) {
            task.setTitle(request.title());
        }
        if (request.description() != null) {
            task.setDescription(request.description());
        }
        if (request.status() != null) {
            task.setStatus(request.status());
        }
        if (request.priority() != null) {
            task.setPriority(request.priority());
        }
        if (request.dueDate() != null) {
            task.setDueDate(request.dueDate());
        }
        Task updatedTask = taskRepository.save(task);
        return TaskResponse.fromEntity(updatedTask);
    }

    // 5. Delete Task: Cek izin sebelum delete
    @Transactional
    public void deleteTask(Long id, User currentUser) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task tidak ditemukan dengan id: " + id));
        validateOwnership(task, currentUser);
        taskRepository.delete(task);
    }

    // 6. Update Task Status: Cek izin sebelum update status
    @Transactional
    public TaskResponse updateTaskStatus(Long id, UpdateStatusRequest request, User currentUser) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task tidak ditemukan dengan id: " + id));
        validateOwnership(task, currentUser);
        task.setStatus(request.status());
        Task updateTask = taskRepository.save(task);
        return TaskResponse.fromEntity(updateTask);
    }

    // 7. Get Task Stats: Admin melihat stats semua, user melihat stats miliknya

    @Transactional
    public TaskStatResponse getTaskStats(User currentUser) {
        LocalDateTime now = LocalDateTime.now();
        if (currentUser.getRole() == Role.ADMIN) {
            long total = taskRepository.count();
            long todo = taskRepository.countByStatus(TaskStatus.TODO);
            long inProgress = taskRepository.countByStatus(TaskStatus.IN_PROGRESS);
            long done = taskRepository.countByStatus(TaskStatus.DONE);

            long high = taskRepository.countByPriority(TaskPriority.HIGH);
            long medium = taskRepository.countByPriority(TaskPriority.MEDIUM);
            long low = taskRepository.countByPriority(TaskPriority.LOW);
            long overdue = taskRepository.countByDueDateBeforeAndStatusNot(now, TaskStatus.DONE);

            return new TaskStatResponse(total, todo, inProgress, done, high, medium, low, overdue);
        } else {
            Long userId = currentUser.getId();
            long total = taskRepository.countByUserId(userId);
            long todo = taskRepository.countByUserIdAndStatus(userId, TaskStatus.TODO);
            long inProgress = taskRepository.countByUserIdAndStatus(userId, TaskStatus.IN_PROGRESS);
            long done = taskRepository.countByUserIdAndStatus(userId, TaskStatus.DONE);
            long high = taskRepository.countByUserIdAndPriority(userId, TaskPriority.HIGH);
            long medium = taskRepository.countByUserIdAndPriority(userId, TaskPriority.MEDIUM);
            long low = taskRepository.countByUserIdAndPriority(userId, TaskPriority.LOW);
            long overdue = taskRepository.countByUserIdAndDueDateBeforeAndStatusNot(userId, now, TaskStatus.DONE);
            return new TaskStatResponse(total, todo, inProgress, done, high, medium, low, overdue);
        }
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> searchTasks(
            String keyword,
            TaskStatus status,
            TaskPriority priority,
            int page,
            int size,
            String sortBy,
            String sortDir,
            User currentUser) {
        // 1. Tentukan pengurutan (ASC / DESC)
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // 2. Buat objek Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // 3. Jika bukan ADMIN, batasi pencarian hanya untuk userId milik currentUser
        Long userId = (currentUser.getRole() == Role.ADMIN) ? null : currentUser.getId();

        // 4. Jalankan query pencarian
        Page<Task> taskPage = taskRepository.searchTasks(userId, status, priority, keyword, pageable);

        // 5. Transformasi Page<Task> menjadi Page<TaskResponse>
        return taskPage.map(TaskResponse::fromEntity);
    }

    // Helper method untuk memvalidasi kepemilikan task
    private void validateOwnership(Task task, User currentUser) {
        if (currentUser.getRole() != Role.ADMIN && !task.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Anda tidak memiliki izin mengakses data task ini");
        }
    }

}
