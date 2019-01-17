package com.example.demo.dtoservice;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CreateCategoryDto;
import com.example.demo.dto.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryDtoService {
    public Page<CategoryDto> findAll(Pageable pageable, SearchRequest searchReqeuest);
    public CategoryDto create(CreateCategoryDto createCategoryDto);
}
