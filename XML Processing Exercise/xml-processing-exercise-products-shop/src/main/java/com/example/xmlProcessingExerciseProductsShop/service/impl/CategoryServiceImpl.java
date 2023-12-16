package com.example.xmlProcessingExerciseProductsShop.service.impl;

import com.example.xmlProcessingExerciseProductsShop.model.dto.CategorySeedDto;
import com.example.xmlProcessingExerciseProductsShop.model.entity.Category;
import com.example.xmlProcessingExerciseProductsShop.repository.CategoryRepository;
import com.example.xmlProcessingExerciseProductsShop.service.CategoryService;
import com.example.xmlProcessingExerciseProductsShop.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categorySeedDtoList) {
        if(categoryRepository.count() > 0){
            return;
        }

        categorySeedDtoList
                .stream()
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        long categoriesCount = categoryRepository.count();

        for (int i = 0; i < 2; i++) {

            long randomId = ThreadLocalRandom.current()
                    .nextLong(1, categoriesCount + 1);
            Category randomCategory = categoryRepository.findById(randomId).orElse(null);
            categories.add(randomCategory);
        }

        return categories;
    }
}


