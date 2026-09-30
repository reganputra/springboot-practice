package org.javafundamental.taskmanagemet.dto;

import org.javafundamental.taskmanagemet.entity.Category;

public record CategoryResponse(
        Long id,
        String name,
        String color) {
    public static CategoryResponse fromEntity(Category category) {
        if (category == null)
            return null;
        return new CategoryResponse(category.getId(), category.getName(), category.getColor());
    }
}
