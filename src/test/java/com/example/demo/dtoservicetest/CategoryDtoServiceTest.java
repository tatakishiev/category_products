package com.example.demo.dtoservicetest;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CreateCategoryDto;
import com.example.demo.dto.SearchRequest;
import com.example.demo.dtoservice.CategoryDtoService;
import com.example.demo.dtoservice.impl.CategoryDtoServiceImpl;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryDtoServiceTest {
    private CategoryDtoService categoryDtoService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @Before
    public void init() {
        categoryDtoService = new CategoryDtoServiceImpl(categoryService, categoryMapper);
    }

    @Test
    public void findAll_shouldFindAndReturnPageOfCategoryDto() {
        Page<Category> categoryPage = createCategoryPage();
        Page<CategoryDto> categoryDtoPage = createCategoryDtoPage();
        Category category = createCategory(1L);
        CategoryDto categoryDto = createCategoryDto(1L);

        when(categoryService.getAll(Pageable.unpaged(), "")).thenReturn(categoryPage);
        when(categoryMapper.convertToDto(category)).thenReturn(categoryDto);

        Page<CategoryDto> expectedValue = categoryDtoService.findAll(Pageable.unpaged(), createSearchRequest());
        assertEquals(expectedValue.getTotalElements(), categoryDtoPage.getTotalElements());
    }

    @Test
    public void create_validCategoryDto_shouldCreateAndReturnCategoryDto() {
        Category category = createCategory(1L);
        CategoryDto categoryDto = createCategoryDto(1L);
        CreateCategoryDto createCategoryDto = getCreateCategoryDto();

        when(categoryService.create(category)).thenReturn(category);
        when(categoryMapper.convertToDto(category)).thenReturn(categoryDto);

        CategoryDto expectedValue = categoryDtoService.create(createCategoryDto);

        assertEquals(categoryDto, expectedValue);
    }

    private CreateCategoryDto getCreateCategoryDto() {
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName("Phones");

        return createCategoryDto;
    }

    private SearchRequest createSearchRequest() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSearchRequest("");

        return searchRequest;
    }

    private Page<Category> createCategoryPage() {
        List<Category> categories = IntStream.range(1, 10).mapToObj(x -> createCategory((long) x)
        ).collect(Collectors.toList());

        return new PageImpl<>(categories);
    }

    private Page<CategoryDto> createCategoryDtoPage() {
        List<CategoryDto> categoryDtos= IntStream.range(1, 10).mapToObj(x -> createCategoryDto((long) x)
        ).collect(Collectors.toList());

        return new PageImpl<>(categoryDtos);
    }

    private CategoryDto createCategoryDto(Long id) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName("Phones");

        return categoryDto;
    }


    private Category createCategory(Long id) {
        Category category = new Category();
        category.setName("Phones");
        category.setId(id);

        return category;
    }

}
