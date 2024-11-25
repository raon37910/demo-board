package com.raon.service.category.model;

import com.raon.domain.category.CategoryEntity;

public record Category (
        String name
){
    public CategoryEntity toEntity() {
        return new CategoryEntity(null, name);
    }
}
