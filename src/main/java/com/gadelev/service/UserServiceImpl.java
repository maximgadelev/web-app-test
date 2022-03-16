package com.gadelev.service;

import com.gadelev.config.MailConfig;
import com.gadelev.dto.CreateUserDto;
import com.gadelev.dto.UserDto;
import com.gadelev.model.User;
import com.gadelev.repo.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder, JavaMailSender javaMailSender, MailConfig mailConfig) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.javaMailSender = javaMailSender;
        this.mailConfig = mailConfig;
    }


    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id).stream().map(UserDto::fromModel).findFirst().orElse(null);
    }

    @Override
    public UserDto save(CreateUserDto createUserDto) {
        User user = new User(createUserDto.getName(), createUserDto.getEmail());
        user.setPassword(encoder.encode(createUserDto.getPassword()));
        return UserDto.fromModel(userRepository.save(user));
    }

    @Override
    public UserDto findByEmail(String email) {
       return  UserDto.fromModel(userRepository.findByEmail(email));
    }

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user != null) {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDto signUp(CreateUserDto createUserDto, String url) {
        String code = RandomString.make(64);
        String encodedPassword = encoder.encode(createUserDto.getPassword());
        User user = new User(createUserDto.getName(), createUserDto.getEmail(), code, encodedPassword);
        User savedUser = userRepository.save(user);
        sendVerificationMail(createUserDto.getEmail(), createUserDto.getName(), code, url);
        return UserDto.fromModel(user);
    }

    @Override
    public void sendVerificationMail(String mail, String name, String code, String url) {
        String from = mailConfig.getFrom();
        String sender = mailConfig.getSender();
        String subject = mailConfig.getSubject();
        String content = mailConfig.getContent();


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from, sender);

            helper.setTo(mail);
            helper.setSubject(subject);

            content = content.replace("{name}", name);
            content = content.replace("{url}", url + "/verify?code=" + code);

            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}

