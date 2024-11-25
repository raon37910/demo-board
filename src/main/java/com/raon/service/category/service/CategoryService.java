package com.raon.service.category.service;

import com.raon.domain.category.CategoryReader;
import com.raon.domain.category.CategoryWriter;
import com.raon.service.category.model.Category;
import com.raon.support.error.BoardException;
import com.raon.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryWriter categoryWriter;
    private final CategoryReader categoryReader;

    public Category create(Category category) {
        categoryWriter.create(category.toEntity());
        return category;
    }

    public Category update(Category category) {
        categoryReader.findById(category.id())
                .orElseThrow(() -> new BoardException(ErrorType.VALIDATION_ERROR));

        categoryWriter.update(category.toEntity());
        return category;
    }
}
