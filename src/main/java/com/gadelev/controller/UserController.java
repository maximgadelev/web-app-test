package com.gadelev.controller;

import com.gadelev.PasswordHelper;
import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;
import com.gadelev.model.User;
import com.gadelev.repo.UserRepository;
import com.gadelev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

  private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Iterable<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/user")
    public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
        return userService.save(user);
    }
}