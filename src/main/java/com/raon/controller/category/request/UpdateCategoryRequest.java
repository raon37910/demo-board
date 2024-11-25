package com.raon.controller.category.request;

import com.raon.service.category.model.Category;

public record UpdateCategoryRequest(
        String name
) {
    public Category toModel(Integer id) {
        return new Category(id, name);
    }
}
