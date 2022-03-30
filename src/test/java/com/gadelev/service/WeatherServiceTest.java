package com.gadelev.service;

import com.gadelev.model.Weather;
import com.gadelev.repo.RequestRepository;
import com.gadelev.repo.WeatherRepository;
import com.gadelev.repository.WeatherRepositoryTest;
import liquibase.pro.packaged.W;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class WeatherServiceTest {
    @TestConfiguration
    public static class WeatherServiceTestConf {
        @MockBean
        private WeatherRepository weatherRepository;

        @Bean
        public WeatherService weatherService() {
            return new WeatherServiceImpl(weatherRepository);
        }
    }

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void getAllByCity() {
        Assert.assertTrue(weatherService.getAllByCity("Moscow").isEmpty());
    }

    @Test
    public void getFindAll() {
        Weather weather = new Weather();
        weather.setId(1);
        weather.setCity("Kazan");
        weather.setHumidity("123");
        weather.setEmail("test@mail.ru");
        List<Weather> weathers = new ArrayList<>();
        weathers.add(weather);
        given(weatherRepository.findAll()).willReturn(weathers);
        Assert.assertFalse(weatherService.findAll().isEmpty());

    }
}
