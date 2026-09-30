package org.javafundamental.taskmanagemet.service;

import java.util.List;
import org.javafundamental.taskmanagemet.exception.ResourceNotFoundException;

import org.javafundamental.taskmanagemet.dto.CategoryRequest;
import org.javafundamental.taskmanagemet.dto.CategoryResponse;
import org.javafundamental.taskmanagemet.entity.Category;
import org.javafundamental.taskmanagemet.entity.User;
import org.javafundamental.taskmanagemet.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponse createCategory(CategoryRequest request, User currentUser) {
        Category category = new Category();
        category.setName(request.name());
        category.setColor(request.color());
        category.setUser(currentUser);
        Category saveCategory = categoryRepository.save(category);
        return CategoryResponse.fromEntity(saveCategory);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getUserCategories(User currrentUser) {
        List<Category> categories = categoryRepository.findByUserId(currrentUser.getId());
        return categories.stream()
                .map(CategoryResponse::fromEntity).toList();
    }

    @Transactional
    public void deleteCategories(Long id, User currentUser) {
        Category category = categoryRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan"));
        categoryRepository.delete(category);
    }
}
