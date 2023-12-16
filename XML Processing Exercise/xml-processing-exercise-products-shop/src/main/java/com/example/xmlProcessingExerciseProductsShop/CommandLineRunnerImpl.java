package com.example.xmlProcessingExerciseProductsShop;

import com.example.xmlProcessingExerciseProductsShop.model.dto.CategorySeedRootDto;
import com.example.xmlProcessingExerciseProductsShop.model.dto.ProductSeedDto;
import com.example.xmlProcessingExerciseProductsShop.model.dto.ProductSeedRootDto;
import com.example.xmlProcessingExerciseProductsShop.model.dto.UserSeedRootDto;
import com.example.xmlProcessingExerciseProductsShop.service.CategoryService;
import com.example.xmlProcessingExerciseProductsShop.service.ProductService;
import com.example.xmlProcessingExerciseProductsShop.service.UserService;
import com.example.xmlProcessingExerciseProductsShop.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String RESOURCE_FILE_PATH = "src/main/resources/files/";
    private static final String CATEGORIES_FILE_NAME = "categories.xml";
    private static final String USERS_FILE_NAME = "users.xml";
    private static final String PRODUCTS_FILE_NAME = "products.xml";

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;


    public CommandLineRunnerImpl(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

    }

    private void seedData() throws JAXBException {

        CategorySeedRootDto categorySeedRootDto = xmlParser
                .fromFile(RESOURCE_FILE_PATH + CATEGORIES_FILE_NAME, CategorySeedRootDto.class);

        categoryService.seedCategories(categorySeedRootDto.getCategorySeedDtoList());

        UserSeedRootDto userSeedRootDto = xmlParser
                .fromFile(RESOURCE_FILE_PATH + USERS_FILE_NAME, UserSeedRootDto.class);

        userService.seedUsers(userSeedRootDto.getUsers());


        ProductSeedRootDto productSeedRootDto = xmlParser
                .fromFile(RESOURCE_FILE_PATH + PRODUCTS_FILE_NAME, ProductSeedRootDto.class);

        productService.seedProducts(productSeedRootDto.getProducts());
    }
}
