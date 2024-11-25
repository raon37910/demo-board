package com.raon.service.category.model;

import com.raon.domain.category.CategoryEntity;

public record Category (
        Integer id,
        String name
){
    public CategoryEntity toEntity() {
        return new CategoryEntity(id, name);
    }
}
