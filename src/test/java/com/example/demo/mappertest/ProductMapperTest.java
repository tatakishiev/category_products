package com.example.demo.mappertest;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.enums.ProductType;
import com.example.demo.mapper.MapperImpl.ProductMapperImpl;
import com.example.demo.mapper.ProductMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {
    private ProductMapper productMapper;

    @Before
    public void init() {
        productMapper = new ProductMapperImpl();
    }

    @Test
    public void convertToDto_validUser_shouldConvertAndReturnProductDto() {
        Product product = createProduct();
        ProductDto productDto = createProductDto();

        ProductDto expectedValue = productMapper.convertToDto(product);

        assertEquals(expectedValue, productDto);
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName("Phones");
        category.setId(1L);

        return category;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setType(ProductType.PRIVATE);
        product.setPrice(BigDecimal.valueOf(1000));
        product.setName("wow");
        product.setCategory(createCategory());

        return product;
    }

    private ProductDto createProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("wow");
        productDto.setPrice(BigDecimal.valueOf(1000));
        productDto.setType(ProductType.PRIVATE);

        return productDto;
    }
}
