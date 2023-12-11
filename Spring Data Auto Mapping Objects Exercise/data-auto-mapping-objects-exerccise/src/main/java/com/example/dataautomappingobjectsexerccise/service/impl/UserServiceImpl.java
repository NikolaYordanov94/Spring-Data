package com.example.dataautomappingobjectsexerccise.service.impl;

import com.example.dataautomappingobjectsexerccise.model.dto.UserLoginDto;
import com.example.dataautomappingobjectsexerccise.model.dto.UserRegisterDto;
import com.example.dataautomappingobjectsexerccise.model.entity.User;
import com.example.dataautomappingobjectsexerccise.repository.UserRepository;
import com.example.dataautomappingobjectsexerccise.service.UserService;
import com.example.dataautomappingobjectsexerccise.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if(!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            System.out.println("Wrong confirm password");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violations =
                validationUtil.violations(userRegisterDto);

        if(!violations.isEmpty()){
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        userRepository.save(user);

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations =
                validationUtil.violations(userLoginDto);

        if(!violations.isEmpty()){
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = userRepository
                .findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);

        if(user == null){
            System.out.println("Incorrect username / password");
            return;
        }

        loggedInUser = user;
        System.out.printf("Successfully logged in %s%n", user.getFullName().split(" ")[0]);
    }

    @Override
    public void logout() {
        if(loggedInUser == null){
            System.out.println("Cannot log out. No user was logged in.");
            return;
        }else{
            System.out.printf("User %s successfully logged out%n", loggedInUser.getFullName().split(" ")[0]);
            loggedInUser = null;
        }
    }
}
