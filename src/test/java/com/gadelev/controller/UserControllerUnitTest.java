package com.gadelev.controller;

import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;
import com.gadelev.model.User;
import com.gadelev.repo.UserRepository;
import com.gadelev.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void init() {
        User user = new User();
        user.setName("testName");
        user.setPassword("password");
        user.setId(1);
        user.setVerificationCode("1112");
        given(userService.getAll()).willReturn(Arrays.asList(UserDto.fromModel(user)));
        given(userService.findById(1)).willReturn(UserDto.fromModel(user));
        given(userService.verify("1112")).willReturn(true);;
    }

    @Test
    public void testGetAllUser() throws Exception {
        mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("testName"));
    }

    @Test
    public void testGetCode() throws Exception {
        mockMvc.perform(get("/verify?code=1112"))
                .andExpect(status().isOk())
                .andExpect(content().string("verification_success1"));
        mockMvc.perform(get("/verify?code=1111"))
                .andExpect(status().isOk())
                .andExpect(content().string("verification_failed1"));
    }

    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("testName"));
    }
@Test
    public void testSignUp() throws Exception{
    CreateUserDto createUserDto = new CreateUserDto();
    createUserDto.setName("Maxim");
    createUserDto.setPassword("testPassword");
    createUserDto.setEmail("gadelev@mail.ru");

    given(userService.signUp(any(CreateUserDto.class), anyString()))
            .willReturn(new UserDto(1, "Maxim", "gadelev@mail.ru", "testPassword"));
    mockMvc.perform(post("/sign_up")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .flashAttr("user", createUserDto)
            )
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect(view().name("sign_up_success"));

}
}
