package com.example.xmlProcessingExerciseProductsShop.service;

import com.example.xmlProcessingExerciseProductsShop.model.dto.CategorySeedDto;
import com.example.xmlProcessingExerciseProductsShop.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {


    void seedCategories(List<CategorySeedDto> categorySeedDtoList);

    Set<Category> getRandomCategories();
}
