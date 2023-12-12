package com.example.jsonProcessingExerciseProductsShop.service;

import com.example.jsonProcessingExerciseProductsShop.model.dto.UserWithSoldProductsDto;
import com.example.jsonProcessingExerciseProductsShop.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    User findRandomUser();

    List<UserWithSoldProductsDto> findAllSuccessfullySoldProducts();

}
