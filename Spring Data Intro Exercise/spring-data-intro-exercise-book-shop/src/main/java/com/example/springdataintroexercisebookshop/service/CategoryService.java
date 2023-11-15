package com.example.springdataintroexercisebookshop.service;

import com.example.springdataintroexercisebookshop.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();

}
