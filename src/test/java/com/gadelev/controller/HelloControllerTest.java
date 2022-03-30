package com.gadelev.controller;

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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {
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
        given(userService.verify("1112")).willReturn(true);
    }
    @Test
    public void testGetIndexPage() throws Exception {
        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void testGetHomePage() throws Exception{
        mockMvc.perform(get("/home")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    public void getSignUpTest() throws Exception{
        mockMvc.perform(get("/sign_up"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("sign_up"));

    }
}
