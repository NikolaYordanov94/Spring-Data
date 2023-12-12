package com.example.jsonProcessingExerciseProductsShop.service.Impl;

import com.example.jsonProcessingExerciseProductsShop.constants.GlobalConstants;
import com.example.jsonProcessingExerciseProductsShop.model.dto.ProductNameAndPriceDto;
import com.example.jsonProcessingExerciseProductsShop.model.dto.ProductSeedDto;
import com.example.jsonProcessingExerciseProductsShop.model.entity.Product;
import com.example.jsonProcessingExerciseProductsShop.repository.ProductRepository;
import com.example.jsonProcessingExerciseProductsShop.service.CategoryService;
import com.example.jsonProcessingExerciseProductsShop.service.ProductService;
import com.example.jsonProcessingExerciseProductsShop.service.UserService;
import com.example.jsonProcessingExerciseProductsShop.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_FILE_NAME = "products.json";

    private final ProductRepository productRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts() throws IOException {

        if(productRepository.count() > 0){
            return;
        }

        String fileContent = Files
                .readString(Path.of(GlobalConstants.RESOURCES_FILE_PATH + PRODUCTS_FILE_NAME));

        ProductSeedDto[] productSeedDtos = gson
                .fromJson(fileContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);

                    product.setSeller(userService.findRandomUser());

                    if(productSeedDto.getPrice().compareTo(BigDecimal.valueOf(750L)) < 0){
                        product.setBuyer(userService.findRandomUser());
                    }

                    product.setCategories(categoryService.findRandomCategories());
                    return product;
                }).forEach(productRepository::save);
    }

    @Override
    public List<ProductNameAndPriceDto> findAllProductsIntRangeOrderByPrice(BigDecimal lower, BigDecimal upper) {
        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(lower, upper)
                .stream()
                .map(product -> {
                    ProductNameAndPriceDto productNameAndPriceDto = modelMapper
                            .map(product, ProductNameAndPriceDto.class);

                    productNameAndPriceDto.setSeller(String.format("%s, %s",
                            product.getSeller().getFirstName(), product.getSeller().getLastName()));

                    return productNameAndPriceDto;
                }).collect(Collectors.toList());

    }
}
