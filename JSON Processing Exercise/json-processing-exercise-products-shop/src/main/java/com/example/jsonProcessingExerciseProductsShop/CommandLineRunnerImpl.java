package com.example.jsonProcessingExerciseProductsShop;

import com.example.jsonProcessingExerciseProductsShop.model.dto.ProductNameAndPriceDto;
import com.example.jsonProcessingExerciseProductsShop.model.dto.UserWithSoldProductsDto;
import com.example.jsonProcessingExerciseProductsShop.service.CategoryService;
import com.example.jsonProcessingExerciseProductsShop.service.ProductService;
import com.example.jsonProcessingExerciseProductsShop.service.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String OUTPUT_FILES_PATH = "C:\\Users\\Nikola\\Desktop\\Spring Data Projects\\JSON Processing Exercise" +
            "\\json-processing-exercise-products-shop\\src\\main\\resources\\files\\out\\";

    private static final String PRODUCT_IN_RANGE_FILE_NAME = "products-in-range";

    private static final String USERS_WITH_SOLD_PRODUCTS_FILE_NAME = "users-with-sold-products";


    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private final Gson gson;


    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, BufferedReader bufferedReader, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.bufferedReader = bufferedReader;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {

        seedData();

        System.out.println("Enter exercise number: ");

        int exerciseNum = Integer.parseInt(bufferedReader.readLine());

        switch (exerciseNum){
            case 1 -> productsInRange();
            case 2 -> successfullySoldProducts();
        }

    }

    private void successfullySoldProducts() throws IOException {
        List<UserWithSoldProductsDto> userWithSoldProductsDtos = userService
                .findAllSuccessfullySoldProducts();

        String content = gson.toJson(userWithSoldProductsDtos);

        writeToFile(OUTPUT_FILES_PATH + USERS_WITH_SOLD_PRODUCTS_FILE_NAME, content);
    }

    private void productsInRange() throws IOException {
        List<ProductNameAndPriceDto> productNameAndPriceDtos = productService
                .findAllProductsIntRangeOrderByPrice(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

        String content = gson.toJson(productNameAndPriceDtos);

        writeToFile(OUTPUT_FILES_PATH + PRODUCT_IN_RANGE_FILE_NAME, content);
    }

    private void writeToFile(String pathToFile, String stringContent) throws IOException {
        Files
                .write(Path.of(pathToFile), Collections.singleton(stringContent));
    }

    private void seedData() throws IOException {
        categoryService
                .seedCategories();

        userService
                .seedUsers();

        productService
                .seedProducts();

    }
}
