package com.example.xmlProcessingExerciseProductsShop.model.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement (name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {


    @XmlElement(name = "product")
    private List<ProductSeedDto> products;

    public ProductSeedRootDto() {
    }

    public List<ProductSeedDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSeedDto> products) {
        this.products = products;
    }
}
