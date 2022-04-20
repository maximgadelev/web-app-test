package com.gadelev.controller;

import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;
import com.gadelev.model.User;
import com.gadelev.repo.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.gadelev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Returns all users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Users were get",
            content = {@Content(mediaType = "application/json")})})
    @GetMapping("/user")
    @ResponseBody
    public Iterable<UserDto> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Returns user by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Users was get by id",
            content = {@Content(mediaType = "application/json")})})
    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto get(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @Operation(summary = "Returns sign up success view")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success view was get",
            content = {@Content(mediaType = "text/html")})})
    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.signUp(userDto, url);
        return "sign_up_success";
    }
    @Operation(summary = "Returns verification view")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Verify view was get",
            content = {@Content(mediaType = "text/html")})})
    @GetMapping("/verify")
    @ResponseBody
    public String verification(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success1";
        } else {
            return "verification_failed1";
        }
    }
}