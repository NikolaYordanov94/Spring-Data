package com.example.xmlProcessingExerciseProductsShop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "categories")
public class Category extends BaseEntity{

    @Column (name = "name", length = 15, nullable = false, unique = true)
    private String name;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
