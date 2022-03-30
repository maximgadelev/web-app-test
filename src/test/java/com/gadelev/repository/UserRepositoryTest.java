package com.gadelev.repository;

import com.gadelev.dto.UserDto;
import com.gadelev.model.User;
import com.gadelev.repo.UserRepository;
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

import java.util.Arrays;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testTEST");
        user.setVerificationCode("11112");
        testEntityManager.persistAndFlush(user);
    }

    @Test
    public void testGetUserByEmail() {
        Optional<User> result = userRepository.getByEmail("test@mail.ru");
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void testGetUserByCode() {
        User resultUser = userRepository.findByVerificationCode("11112");
        Assert.assertNotNull(resultUser);
        Assert.assertEquals("Ivan", resultUser.getName());
    }

    @Test
    public void testFindByEmail() {
        User resultUser = userRepository.findByEmail("test@mail.ru");
        Assert.assertNotNull(resultUser);
        Assert.assertEquals("Ivan", resultUser.getName());
    }
}