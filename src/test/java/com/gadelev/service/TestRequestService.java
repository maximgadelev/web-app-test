package com.gadelev.service;

import com.gadelev.dto.RequestDto;
import com.gadelev.model.Request;
import com.gadelev.model.User;
import com.gadelev.model.Weather;
import com.gadelev.repo.RequestRepository;
import com.gadelev.repo.UserRepository;
import liquibase.pro.packaged.W;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class TestRequestService {
    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @MockBean
        private RequestRepository requestRepository;
        @Bean
        public RequestService requestService() {
            return new RequestServiceImpl(requestRepository);
        }
    }
    @Autowired
    private RequestService requestService;
    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void findAllByUserId(){
        Assert.assertTrue(requestService.findAllByUserId(1).isEmpty());
    }
    @Test
    public void findAllByCity(){
        Assert.assertTrue(requestService.findAllCity("Moscow").isEmpty());
    }
    @Test
    public void save(){
        User user = new User();
        user.setEmail("test@mail.ru");
        Weather weather = new Weather();
        weather.setId(1);
        Request request = new Request();
        request.setId(1);
        request.setCity("Moscow");
        request.setUser(user);
        request.setWeather(weather);
        given(requestRepository.save(request)).willReturn(request);
        RequestDto requestDto =requestService.save(request);
        Assert.assertNotNull(requestDto);
    }
}
