package com.example.demo.dto;

import com.example.demo.enums.ProductType;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateProductDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;

    @NotNull
    private ProductType type;
}
