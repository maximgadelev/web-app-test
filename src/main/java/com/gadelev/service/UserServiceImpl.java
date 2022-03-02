package com.gadelev.service;

import com.gadelev.PasswordHelper;
import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;
import com.gadelev.model.User;
import com.gadelev.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id).stream().map(UserDto::fromModel).findFirst().orElse(null);
    }

    @Override
    public UserDto save(CreateUserDto user) {
        return UserDto.fromModel(userRepository.save(new User(user.getName(), user.getEmail(),
                PasswordHelper.encrypt(user.getPassword()))));
    }

    @Override
    public UserDto findByEmail(String email) {
       return  UserDto.fromModel(userRepository.findByEmail(email));
    }
}

