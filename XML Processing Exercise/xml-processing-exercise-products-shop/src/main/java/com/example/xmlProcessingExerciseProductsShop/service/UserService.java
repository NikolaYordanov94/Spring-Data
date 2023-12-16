package com.example.xmlProcessingExerciseProductsShop.service;

import com.example.xmlProcessingExerciseProductsShop.model.dto.UserSeedDto;
import com.example.xmlProcessingExerciseProductsShop.model.entity.User;

import java.util.List;

public interface UserService {

    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();
}
