package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;
}
