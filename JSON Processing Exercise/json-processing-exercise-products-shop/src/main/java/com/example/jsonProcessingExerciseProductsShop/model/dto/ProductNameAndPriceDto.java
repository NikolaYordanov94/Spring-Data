package com.example.jsonProcessingExerciseProductsShop.model.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductNameAndPriceDto {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String seller;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public ProductNameAndPriceDto() {
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
