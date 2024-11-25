package com.raon.mapper;

import com.raon.domain.category.CategoryEntity;

public interface CategoryMapper {
    void create(String name);
    void update(CategoryEntity categoryEntity);
    CategoryEntity findById(Integer id);
}
