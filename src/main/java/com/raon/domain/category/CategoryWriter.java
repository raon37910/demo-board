package com.raon.domain.category;

import com.raon.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryWriter {

    private final CategoryMapper categoryMapper;

    public void create(CategoryEntity categoryEntity) {
        categoryMapper.create(categoryEntity.getName());
    }
}
