package com.example.demo.servicetest;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.enums.ProductType;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void init() {
        productService = new ProductServiceImpl(productRepository);
    }


    @Test
    public void getAll_withNotEmptySearchString_shouldFindAndReturnPageOfProduct() {
        Category category = createCategory();
        Page<Product> productPage = createProductPage();

        when(productRepository.findAllByCategoryAndNameContainsIgnoreCase(category, "", Pageable.unpaged()))
                .thenReturn(productPage);
        Page<Product> expectedValue = productService.findAll(Pageable.unpaged(), "", category);

        assertEquals(expectedValue, productPage);
    }

    @Test
    public void getById_shouldFindAndReturnProduct() {
        Product product = createProduct(1L);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(product));
        Optional<Product> expectedValue = productService.findById(1L);

        assertEquals(expectedValue.get(), product);
    }

    @Test
    public void create_validEntity_shouldCreateAndReturnCategory() {
        Product product = createProduct(1L);

        when(productRepository.save(product)).thenReturn(product);
        Product expectedValue = productService.create(product);

        assertEquals(expectedValue, product);
    }

    private Page<Product> createProductPage() {
        List<Product> products = IntStream.range(1, 2).mapToObj(x -> createProduct((long) x)
        ).collect(Collectors.toList());

        return new PageImpl<>(products);
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName("Phones");
        category.setId(1L);

        return category;
    }

    private Product createProduct(Long id) {
        Product product = new Product();
        product.setCategory(createCategory());
        product.setName("Iphone");
        product.setPrice(BigDecimal.valueOf(1300));
        product.setType(ProductType.PRIVATE);
        product.setId(id);

        return product;
    }

}
