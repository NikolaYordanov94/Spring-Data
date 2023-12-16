package com.example.xmlProcessingExerciseProductsShop.service.impl;

import com.example.xmlProcessingExerciseProductsShop.model.dto.UserSeedDto;
import com.example.xmlProcessingExerciseProductsShop.model.entity.User;
import com.example.xmlProcessingExerciseProductsShop.repository.UserRepository;
import com.example.xmlProcessingExerciseProductsShop.service.UserService;
import com.example.xmlProcessingExerciseProductsShop.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;



    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        if(userRepository.count() > 0){
            return;
        }

        users
                .stream()
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long randomId = ThreadLocalRandom.current()
                .nextLong(1, userRepository.count() + 1);

        return userRepository.findById(randomId)
                .orElse(null);
    }
}
