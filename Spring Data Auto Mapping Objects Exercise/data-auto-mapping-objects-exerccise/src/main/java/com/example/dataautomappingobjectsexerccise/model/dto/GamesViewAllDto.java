package com.example.dataautomappingobjectsexerccise.model.dto;

import java.math.BigDecimal;

public class GamesViewAllDto {
    private String title;

    private BigDecimal price;

    public GamesViewAllDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public GamesViewAllDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
