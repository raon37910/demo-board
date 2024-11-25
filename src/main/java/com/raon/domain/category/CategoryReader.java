package com.raon.domain.category;

import com.raon.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryReader {
    private final CategoryMapper categoryMapper;

    public Optional<CategoryEntity> findById(Integer id) {
        return Optional.ofNullable(categoryMapper.findById(id));
    }
}
