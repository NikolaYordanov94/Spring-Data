package com.example.jsonProcessingExerciseProductsShop.service.Impl;

import com.example.jsonProcessingExerciseProductsShop.constants.GlobalConstants;
import com.example.jsonProcessingExerciseProductsShop.model.dto.CategorySeedDto;
import com.example.jsonProcessingExerciseProductsShop.model.entity.Category;
import com.example.jsonProcessingExerciseProductsShop.repository.CategoryRepository;
import com.example.jsonProcessingExerciseProductsShop.service.CategoryService;
import com.example.jsonProcessingExerciseProductsShop.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_NAME = "categories.json";

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(Gson gson, ValidationUtil validationUtil, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {

        if (categoryRepository.count() > 0){
            return;
        }

        String fileContent = Files
                .readString(Path.of(GlobalConstants.RESOURCES_FILE_PATH + CATEGORIES_FILE_NAME));


        CategorySeedDto[] categorySeedDtos = gson
                .fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int categoryCount = ThreadLocalRandom.current().nextInt(1, 3);
        long totalCategories = categoryRepository.count();

        for (int i = 0; i < categoryCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, totalCategories + 1);

            Category randomCategory =
                    categoryRepository.findById(randomId).orElse(null);

            categories.add(randomCategory);
        }

        return categories;
    }
}
