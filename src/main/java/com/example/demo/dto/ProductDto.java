package com.example.demo.dto;

import com.example.demo.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private ProductType type;
}
