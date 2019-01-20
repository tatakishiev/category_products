package com.example.demo.dtoservicetest;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SearchRequest;
import com.example.demo.dtoservice.ProductDtoService;
import com.example.demo.dtoservice.impl.ProductDtoServiceImpl;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.enums.ProductType;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductDtoServiceTest {
    private ProductDtoService productDtoService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @Before
    public void init() {
        productDtoService = new ProductDtoServiceImpl(
                categoryService,
                productService,
                productMapper
        );
    }

    @Test
    public void findAll_shouldFindAndReturnPageOfProductDto() {
        Product product = createProduct(1L);
        ProductDto productDto = createProductDto(1L);
        Page<Product> productPage = createProductPage();
        Category category = createCategory(1L);
        Page<ProductDto> productDtoPage = createProductDtoPage();

        when(productMapper.convertToDto(product)).thenReturn(productDto);
        when(productService.findAll(Pageable.unpaged(), "", category)).thenReturn(productPage);
        when(categoryService.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));


        Page<ProductDto> expectedValue = productDtoService.findAll(Pageable.unpaged(), createSearchRequest(), 1L);

        assertEquals(expectedValue, productDtoPage);
    }

    @Test
    public void create_validCrateProductDto_shouldCreateAnReturnProductDto() {
        Product product = createProduct(1L);
        ProductDto productDto = createProductDto(1L);
        Category category = createCategory(1L);
        CreateProductDto createProductDto = getCreateProductDto();

        when(categoryService.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));
        when(productService.create(any())).thenReturn(product);
        when(productMapper.convertToDto(product)).thenReturn(productDto);

        ProductDto expectedValue = productDtoService.create(createProductDto, 1L);

        assertEquals(expectedValue, productDto);
    }

    private SearchRequest createSearchRequest() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSearchRequest("");

        return searchRequest;
    }

    private Page<Product> createProductPage() {
        List<Product> products = IntStream.range(1, 2).mapToObj(x -> createProduct((long) x)
        ).collect(Collectors.toList());

        return new PageImpl<>(products);
    }

    private Category createCategory(Long id) {
        Category category = new Category();
        category.setName("Phones");
        category.setId(id);

        return category;
    }

    private Product createProduct(Long id) {
        Product product = new Product();
        product.setCategory(createCategory(1L));
        product.setName("Iphone");
        product.setPrice(BigDecimal.valueOf(1300));
        product.setType(ProductType.PRIVATE);
        product.setId(id);

        return product;
    }

    private CreateProductDto getCreateProductDto() {
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setName("IPhone");
        createProductDto.setPrice(BigDecimal.valueOf(1300));
        createProductDto.setType(ProductType.PRIVATE);

        return createProductDto;
    }

    private ProductDto createProductDto(Long id) {
        ProductDto productDto = new ProductDto();
        productDto.setType(ProductType.PRIVATE);
        productDto.setPrice(BigDecimal.valueOf(1300));
        productDto.setName("IPhone");
        productDto.setId(id);

        return productDto;
    }

    private Page<ProductDto> createProductDtoPage() {
        List<ProductDto> productDtos= IntStream.range(1, 2).mapToObj(x -> createProductDto((long) x)
        ).collect(Collectors.toList());

        return new PageImpl<>(productDtos);
    }
}
