package com.gadelev.service;

import com.gadelev.config.MailConfig;
import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;
import com.gadelev.model.User;
import com.gadelev.repo.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
public class TestUserService {
    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @MockBean
        private UserRepository userRepository;

        @MockBean
        private BCryptPasswordEncoder encoder;

        @MockBean
        private JavaMailSender javaMailSender;

        @MockBean
        private MailConfig mailConfig;

        @Bean
        public UserService userService() {
            return new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);
        }


    }
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testGetAll() {
        Assert.assertTrue(userService.getAll().isEmpty());
    }

    @Test
    public void findById() {
        UserDto result = userService.findById(1);
        Assert.assertNull(result);
    }

    @Test
    public void verify() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testTEST");
        user.setVerificationCode("11112");
        given(userRepository.findByVerificationCode("11112")).willReturn(user);
        Assert.assertTrue(userService.verify("11112"));
        Assert.assertFalse(userService.verify("1111"));
    }
}
