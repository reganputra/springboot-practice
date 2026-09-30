package org.javafundamental.taskmanagemet.controller;

import java.util.List;

import org.javafundamental.taskmanagemet.dto.CategoryRequest;
import org.javafundamental.taskmanagemet.dto.CategoryResponse;
import org.javafundamental.taskmanagemet.entity.User;
import org.javafundamental.taskmanagemet.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(categoryService.getUserCategories(currentUser));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request,
            @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request, currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        categoryService.deleteCategories(id, currentUser);
        return ResponseEntity.noContent().build();
    }

}
