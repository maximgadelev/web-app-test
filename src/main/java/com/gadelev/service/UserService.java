package com.gadelev.service;

import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto findById(Integer id);

    UserDto save(CreateUserDto user);
    UserDto findByEmail(String email);
}
