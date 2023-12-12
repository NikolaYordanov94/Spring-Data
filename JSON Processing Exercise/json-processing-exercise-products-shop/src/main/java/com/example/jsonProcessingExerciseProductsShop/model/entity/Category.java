package com.example.jsonProcessingExerciseProductsShop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table (name = "categories")
public class Category extends BaseEntity{

    @Column (length = 15, nullable = false, unique = true)
    private String name;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
