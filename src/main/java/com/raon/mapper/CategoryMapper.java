package com.raon.mapper;

import com.raon.domain.category.CategoryEntity;

public interface CategoryMapper {
    void create(String name);

    void update(CategoryEntity categoryEntity);

    void delete(Integer id);

    CategoryEntity findById(Integer id);
}
