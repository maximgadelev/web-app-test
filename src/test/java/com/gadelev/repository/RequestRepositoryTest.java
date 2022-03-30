package com.gadelev.repository;

import com.gadelev.model.Request;
import com.gadelev.model.User;
import com.gadelev.model.Weather;
import com.gadelev.repo.RequestRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RequestRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RequestRepository requestRepository;

    @Before
    public void init() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testTEST");

        User user2 = new User();
        user2.setEmail("test2@mail.ru");
        user2.setName("Ivan");
        user2.setPassword("testTEST");

        Weather weather = new Weather();
        weather.setHumidity("1112");
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");

        Weather weather2 = new Weather();
        weather2.setHumidity("111");
        weather2.setEmail("test2@mail.ru");
        weather2.setCity("Moscow");

        Request request = new Request();
        request.setCity("Kazan");
        request.setUser(user);
        request.setWeather(weather);

        Request request1 = new Request();
        request1.setCity("Kazan");
        request1.setUser(user);
        request1.setWeather(weather2);

        testEntityManager.persistAndFlush(user);
        testEntityManager.persistAndFlush(user2);

        testEntityManager.persistAndFlush(weather);
        testEntityManager.persistAndFlush(weather2);

        testEntityManager.persistAndFlush(request);
        testEntityManager.persistAndFlush(request1);
    }

    @Test
    public void testGetByCity() {
List<Request> requestList = requestRepository.findAllByCity("Kazan");
        Assert.assertNotNull(requestList);
    }
    @Test
    public void getByUserId(){
        List<Request> requestList = requestRepository.findAllByUserId(1);
        Assert.assertNotNull(requestList);
    }
}
