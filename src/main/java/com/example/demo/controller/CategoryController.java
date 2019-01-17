package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CreateCategoryDto;
import com.example.demo.dto.SearchRequest;
import com.example.demo.dtoservice.CategoryDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryDtoService categoryDtoService;

    public CategoryController(CategoryDtoService categoryDtoService) {
        this.categoryDtoService = categoryDtoService;
    }

    @GetMapping
    public Page<CategoryDto> getAll(Pageable pageable, SearchRequest searchRequest) {
        return categoryDtoService.findAll(pageable, searchRequest);
    }

    @PostMapping
    public CategoryDto create(@Validated @RequestBody CreateCategoryDto createCategoryDto) {
        return categoryDtoService.create(createCategoryDto);
    }
}
