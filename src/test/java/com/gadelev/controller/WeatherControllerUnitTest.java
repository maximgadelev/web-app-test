package com.gadelev.controller;

import com.gadelev.dto.WeatherDto;
import com.gadelev.model.Weather;
import com.gadelev.repo.RequestRepository;
import com.gadelev.repo.UserRepository;
import com.gadelev.repo.WeatherRepository;
import com.gadelev.service.RequestService;
import com.gadelev.service.UserService;
import com.gadelev.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private WeatherRepository weatherRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private RequestService requestService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RequestRepository requestRepositoryy;


    @MockBean
    private BCryptPasswordEncoder encoder;
    @Before
    public void init() {
        Weather weather = new Weather();
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");

        Weather weather2 = new Weather();
        weather2.setEmail("test2@mail.ru");
        weather2.setCity("Moscow");

        given(weatherService.findAll()).willReturn(Arrays.asList(WeatherDto.fromModel(weather),
                WeatherDto.fromModel(weather2)));
        given(weatherService.getAllByCity("Moscow")).willReturn(Arrays.asList(WeatherDto.fromModel(weather2)));
    }
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/allWeather")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].city").value("Kazan"));
    }
    @Test
    public void testGetWeatherByCity() throws Exception {
        mockMvc.perform(get("/weather/city/Moscow")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city").value("Moscow"));
    }
//    @Test
//    public void testGetWeatherJson() throws Exception{
//        User user = new User();
//        user.setName("testName");
//        user.setEmail("test@mail.ru");
//        user.setPassword("12345678");
//        mockMvc.perform(get("/weather")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .with(
//                                httpBasic("test@mail.ru",encoder.encode("12345678"))
//                        ))
//                .andExpect(status().isOk());
//    }

}
