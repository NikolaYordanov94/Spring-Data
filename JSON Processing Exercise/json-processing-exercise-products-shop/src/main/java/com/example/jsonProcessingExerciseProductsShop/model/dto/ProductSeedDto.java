package com.example.jsonProcessingExerciseProductsShop.model.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductSeedDto {

    @Expose
    @Size(min = 3)
    private String name;
    @Expose
    private BigDecimal price;

    public ProductSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
