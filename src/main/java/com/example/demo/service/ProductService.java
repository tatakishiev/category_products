package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    public Page<Product> findAll(Pageable pageable, String searchString, Category category);

    public Optional<Product> findById(Long id);

    public Product create(Product product);
}
