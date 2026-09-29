package org.javafundamental.taskmanagemet.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.javafundamental.taskmanagemet.entity.Task;
import org.javafundamental.taskmanagemet.entity.TaskPriority;
import org.javafundamental.taskmanagemet.entity.TaskStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

        // Mencari semua task milik user tertentu
        List<Task> findByUserId(Long userId);

        // Mencari task berdasarkan user dan status tertentu
        List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);

        // --- Untuk User Biasa ---
        long countByUserId(Long userId);

        long countByUserIdAndStatus(Long userId, TaskStatus status);

        long countByUserIdAndPriority(Long userId, TaskPriority priority);

        long countByUserIdAndDueDateBeforeAndStatusNot(Long userId, LocalDateTime now, TaskStatus status);

        // --- Untuk Admin (Global) ---
        long countByStatus(TaskStatus status);

        long countByPriority(TaskPriority priority);

        long countByDueDateBeforeAndStatusNot(LocalDateTime now, TaskStatus status);

        @Query("SELECT t FROM Task t WHERE " +
                        "(:userId IS NULL OR t.user.id = :userId) AND " +
                        "(:status IS NULL OR t.status = :status) AND " +
                        "(:priority IS NULL OR t.priority = :priority) AND " +
                        "(:keyword IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
        Page<Task> searchTasks(
                        @Param("userId") Long userId,
                        @Param("status") TaskStatus status,
                        @Param("priority") TaskPriority priority,
                        @Param("keyword") String keyword,
                        Pageable pageable);

}
