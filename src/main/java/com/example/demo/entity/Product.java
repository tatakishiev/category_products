package com.example.demo.entity;

import com.example.demo.enums.ProductType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ProductType type;
}
