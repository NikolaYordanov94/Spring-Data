package com.example.dataautomappingobjectsexerccise.service;

import com.example.dataautomappingobjectsexerccise.model.dto.UserLoginDto;
import com.example.dataautomappingobjectsexerccise.model.dto.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

}
