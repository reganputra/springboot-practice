package org.javafundamental.taskmanagemet.repository;

import java.util.List;
import java.util.Optional;

import org.javafundamental.taskmanagemet.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUserId(Long userId);

    Optional<Category> findByIdAndUserId(Long id, Long userID);

}
