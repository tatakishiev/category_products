package com.example.demo.dtoservice.impl;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SearchRequest;
import com.example.demo.dtoservice.ProductDtoService;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class ProductDtoServiceImpl implements ProductDtoService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductDtoServiceImpl(CategoryService categoryService, ProductService productService, ProductMapper productMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable, SearchRequest searchRequest, Long categoryId) {
        Category category = categoryService.findById(categoryId).orElseThrow(() -> new EntityExistsException("There is no Category With id " + categoryId));

        return productService.findAll(pageable, searchRequest.getSearchRequest(), category).map(productMapper::convertToDto);
    }

    @Override
    public ProductDto create(CreateProductDto createProductDto, Long categoryId) {
        Category category = categoryService.findById(categoryId).orElseThrow(() -> new EntityExistsException("There is no Category With id " + categoryId));
        Product product = productService.create(convertToProduct(createProductDto, category));

        return productMapper.convertToDto(product);
    }

    private Product convertToProduct(CreateProductDto createProductDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setName(createProductDto.getName());
        product.setPrice(createProductDto.getPrice());
        product.setType(createProductDto.getType());

        return product;
    }
}
