package com.example.xmlProcessingExerciseProductsShop.service;

import com.example.xmlProcessingExerciseProductsShop.model.dto.ProductSeedDto;

import java.util.List;

public interface ProductService {

    void seedProducts(List<ProductSeedDto> products);

}
