package com.example.jsonProcessingExerciseProductsShop.model.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;

public class UserSeedDto {
    @Expose
    private String firstName;
    @Expose
    @Size(min = 3)
    private String lastName;
    @Expose
    private int age;

    public UserSeedDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
