package com.example.springdataintroexercisebookshop.service;

import com.example.springdataintroexercisebookshop.model.entity.Category;
import com.example.springdataintroexercisebookshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_PATH =
            "C:\\Users\\Nikola\\Desktop\\Spring Data Projects\\Spring Data Intro Exercise\\spring-data-intro-exercise-book-shop\\src\\main\\resources\\categories.txt";

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {

        if (categoryRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(categoryName -> {
                    Category category = new Category(categoryName);
                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategories() {

        Set<Category> categories = new HashSet<>();
        Integer randomInt = ThreadLocalRandom.current().nextInt(1, 3);

        for (int i = 0; i < randomInt; i++) {
            long randomId = ThreadLocalRandom.current()
                    .nextLong(1, categoryRepository.count() + 1);

            Category category = categoryRepository.findById(randomId)
                    .orElse(null);

            categories.add(category);
        }

        return categories;
    }
}
