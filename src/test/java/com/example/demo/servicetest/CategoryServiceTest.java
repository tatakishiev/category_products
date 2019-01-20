package com.example.demo.servicetest;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void init() {
        this.categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void getAll_withNotEmptySearchString_shouldFindAndReturnPageOfCategory() {

        Page<Category> categoryPage = createCategoryPage();

        when(categoryRepository.findAllByNameContainsIgnoreCase("", Pageable.unpaged())).thenReturn(categoryPage);
        Page<Category> expectedValue = categoryService.getAll(Pageable.unpaged(), "");

        assertEquals(expectedValue, categoryPage);
    }

    @Test
    public void getById_shouldFindAndReturnCategory() {
        Category category = createCategory(1L);

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));
        Optional<Category> expectedValue = categoryService.findById(1L);

        assertEquals(expectedValue.get(), category);
    }

    @Test
    public void create_validEntity_shouldCreateAndReturnCategory() {
        Category category = createCategory(1L);

        when(categoryRepository.save(category)).thenReturn(category);
        Category expectedValue = categoryService.create(category);

        assertEquals(expectedValue, category);
    }

    private Page<Category> createCategoryPage() {
        List<Category> categories = IntStream.range(1, 10).mapToObj(x -> createCategory((long) x)
        ).collect(Collectors.toList());

        return new PageImpl<>(categories);
    }

    private Category createCategory(Long id) {
        Category category = new Category();
        category.setName("Phones");
        category.setId(id);

        return category;
    }
}
