package com.example.jsonProcessingExerciseProductsShop.service;

import com.example.jsonProcessingExerciseProductsShop.model.dto.ProductNameAndPriceDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNameAndPriceDto> findAllProductsIntRangeOrderByPrice(BigDecimal lower, BigDecimal upper);

}
