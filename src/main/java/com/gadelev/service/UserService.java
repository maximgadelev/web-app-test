package com.gadelev.service;

import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto findById(Integer id);


    boolean verify(String verificationCode);

    UserDto signUp(CreateUserDto createUserDto, String url);
    void sendVerificationMail(String mail, String name, String code, String url);
}
