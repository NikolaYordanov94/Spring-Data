package com.example.jsonProcessingExerciseProductsShop.service;

import com.example.jsonProcessingExerciseProductsShop.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();
}
