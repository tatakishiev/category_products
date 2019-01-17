package com.example.demo.service;

import com.example.demo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    public Page<Category> getAll(Pageable pageable, String searchString);

    public Optional<Category> findById(Long id);

    public Category create(Category category);
}
