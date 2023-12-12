package com.example.jsonProcessingExerciseProductsShop.service.Impl;

import com.example.jsonProcessingExerciseProductsShop.constants.GlobalConstants;
import com.example.jsonProcessingExerciseProductsShop.model.dto.UserSeedDto;
import com.example.jsonProcessingExerciseProductsShop.model.dto.UserWithSoldProductsDto;
import com.example.jsonProcessingExerciseProductsShop.model.entity.User;
import com.example.jsonProcessingExerciseProductsShop.repository.UserRepository;
import com.example.jsonProcessingExerciseProductsShop.service.UserService;
import com.example.jsonProcessingExerciseProductsShop.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_NAME = "users.json";

    private final Gson gson;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public UserServiceImpl(Gson gson, UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gson = gson;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void seedUsers() throws IOException {

        if(userRepository.count() > 0){
            return;
        }

        String fileContent = Files
                .readString(Path.of(GlobalConstants.RESOURCES_FILE_PATH + USERS_FILE_NAME));

        UserSeedDto[] userSeedDtos = gson
                .fromJson(fileContent, UserSeedDto[].class);


        Arrays.stream(userSeedDtos)
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);

    }

    @Override
    public User findRandomUser() {

        long randomId = ThreadLocalRandom
                .current().nextLong(1, userRepository.count() + 1);

        return userRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<UserWithSoldProductsDto> findAllSuccessfullySoldProducts() {
        List<User> users = userRepository.findAllByProductsIsNotEmptyOrderByLastNameAscFirstNameAsc();


        return users.stream()
                .map(user -> modelMapper.map(user, UserWithSoldProductsDto.class))
                .collect(Collectors.toList());
    }
}
