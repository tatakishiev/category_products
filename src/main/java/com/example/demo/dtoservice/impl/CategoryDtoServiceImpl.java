package com.example.demo.dtoservice.impl;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CreateCategoryDto;
import com.example.demo.dto.SearchRequest;
import com.example.demo.dtoservice.CategoryDtoService;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryDtoServiceImpl implements CategoryDtoService {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryDtoServiceImpl(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Page<CategoryDto> findAll(Pageable pageable, SearchRequest searchRequest) {
        return categoryService.getAll(pageable, searchRequest.getSearchRequest()).map(categoryMapper::convertToDto);
    }

    @Override
    public CategoryDto create(CreateCategoryDto createCategoryDto) {
        Category category = categoryService.create(convertToCategory(createCategoryDto));

        return categoryMapper.convertToDto(category);
    }

    private Category convertToCategory(CreateCategoryDto createCategoryDto) {
        Category category = new Category();
        category.setName(createCategoryDto.getName());

        return category;
    }
}
