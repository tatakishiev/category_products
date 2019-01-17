package com.example.demo.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;

public interface CategoryMapper {
    public CategoryDto convertToDto(Category category);
}
