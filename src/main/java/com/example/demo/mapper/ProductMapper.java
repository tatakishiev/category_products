package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;

public interface ProductMapper {
    public ProductDto convertToDto(Product product);
}
