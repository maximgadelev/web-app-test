package com.gadelev.controller;

import com.gadelev.dto.RequestDto;
import com.gadelev.model.Request;
import com.gadelev.model.User;
import com.gadelev.model.Weather;
import com.gadelev.repo.RequestRepository;
import com.gadelev.repo.UserRepository;
import com.gadelev.service.RequestService;
import com.gadelev.service.UserService;
import com.gadelev.service.WeatherService;
import liquibase.pro.packaged.R;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestController.class)
public class RequestControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

    @MockBean
    private RequestRepository requestRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Before
    public void init() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testTEST");
        user.setVerificationCode("123");

        User user2 = new User();
        user2.setEmail("test2@mail.ru");
        user2.setName("Ivan");
        user2.setPassword("testTEST");
        user2.setVerificationCode("1234");

        Weather weather = new Weather();
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");

        Weather weather2 = new Weather();
        weather2.setEmail("test2@mail.ru");
        weather2.setCity("Moscow");

        Request request = new Request();
        request.setId(1);
        request.setUser(user);
        request.setWeather(weather);

        Request request1 = new Request();
        request1.setUser(user2);
        request1.setWeather(weather2);
        given(requestService.findAllByUserId(1)).willReturn(Arrays.asList(RequestDto.fromModel(request)));
        given(requestService.findAllCity("Moscow")).willReturn(Arrays.asList(RequestDto.fromModel(request)));
    }
    @Test
    public void testGetRequestsByUserId() throws Exception {
        mockMvc.perform(get("/request/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("1"));
    }
    @Test
    public void testGetRequestsByCity() throws Exception {
        mockMvc.perform(get("/city/Moscow")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("1"));
    }
}
