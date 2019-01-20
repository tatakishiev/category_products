package com.example.demo.mappertest;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.MapperImpl.CategoryMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CategoryMapperService {
    private CategoryMapper categoryMapper;

    @Before
    public void init() {
        categoryMapper = new CategoryMapperImpl();
    }

    @Test
    public void convertToDto_validUser_shouldConvertAndReturnCategoryDto() {
        Category category = createCategory();
        CategoryDto categoryDto = createCategoryDto();

        CategoryDto expectedValue = categoryMapper.convertToDto(category);

        assertEquals(expectedValue, categoryDto);
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName("Phones");
        category.setId(1L);

        return category;
    }

    private CategoryDto createCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("Phones");

        return categoryDto;
    }
}
