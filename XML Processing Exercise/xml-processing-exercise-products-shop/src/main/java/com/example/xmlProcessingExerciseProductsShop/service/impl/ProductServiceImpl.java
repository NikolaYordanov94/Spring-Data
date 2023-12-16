package com.example.xmlProcessingExerciseProductsShop.service.impl;

import com.example.xmlProcessingExerciseProductsShop.model.dto.ProductSeedDto;
import com.example.xmlProcessingExerciseProductsShop.model.entity.Product;
import com.example.xmlProcessingExerciseProductsShop.repository.ProductRepository;
import com.example.xmlProcessingExerciseProductsShop.service.CategoryService;
import com.example.xmlProcessingExerciseProductsShop.service.ProductService;
import com.example.xmlProcessingExerciseProductsShop.service.UserService;
import com.example.xmlProcessingExerciseProductsShop.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedProducts(List<ProductSeedDto> products) {
        if(productRepository.count() > 0){
            return;
        }

        products
                .stream()
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.getRandomUser());

                    if(product.getPrice().compareTo(BigDecimal.valueOf(750L)) < 0){
                        product.setBuyer(userService.getRandomUser());
                    }
                    product.setCategories(categoryService.getRandomCategories());

                    return product;
                }).forEach(productRepository::save);
    }
}
