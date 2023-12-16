package com.example.xmlProcessingExerciseProductsShop.repository;

import com.example.xmlProcessingExerciseProductsShop.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
