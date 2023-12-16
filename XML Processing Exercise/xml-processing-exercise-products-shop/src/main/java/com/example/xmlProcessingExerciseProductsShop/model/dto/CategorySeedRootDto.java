package com.example.xmlProcessingExerciseProductsShop.model.dto;


import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDto {

    @XmlElement(name = "category")
    @Size(min = 3, max = 15)
    private List<CategorySeedDto> categorySeedDtoList;

    public CategorySeedRootDto() {
    }

    public List<CategorySeedDto> getCategorySeedDtoList() {
        return categorySeedDtoList;
    }

    public void setCategorySeedDtoList(List<CategorySeedDto> categorySeedDtoList) {
        this.categorySeedDtoList = categorySeedDtoList;
    }
}
