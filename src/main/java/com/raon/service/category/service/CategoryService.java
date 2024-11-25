package com.raon.service.category.service;

import com.raon.domain.category.CategoryWriter;
import com.raon.service.category.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryWriter categoryWriter;

    public Category create(Category category) {
        categoryWriter.create(category.toEntity());
        return category;
    }
}
