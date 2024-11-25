package com.raon.controller.category.request;

import com.raon.service.category.model.Category;

public record CreateCategoryRequest(
    String name
) {

    public Category toModel() {
        return new Category(name);
    }
}
