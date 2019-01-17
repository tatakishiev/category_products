package com.example.demo.controller;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SearchRequest;
import com.example.demo.dtoservice.ProductDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{categoryId}/products")
public class ProductController {

    private final ProductDtoService productDtoService;

    public ProductController(ProductDtoService productDtoService) {
        this.productDtoService = productDtoService;
    }

    @GetMapping
    public Page<ProductDto> getAllByCategory(@PathVariable Long categoryId, Pageable pageable, SearchRequest searchRequest) {
        return productDtoService.findAll(pageable, searchRequest, categoryId);
    }

    @PostMapping
    public ProductDto create(@PathVariable Long categoryId,@Validated  @RequestBody CreateProductDto createProductDto) {
        return productDtoService.create(createProductDto, categoryId);
    }
}
