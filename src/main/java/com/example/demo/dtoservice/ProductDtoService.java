package com.example.demo.dtoservice;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDtoService {
    public Page<ProductDto> findAll(Pageable pageable, SearchRequest searchRequest, Long categoryId);

    public ProductDto create(CreateProductDto createProductDto, Long categoryId);
}
