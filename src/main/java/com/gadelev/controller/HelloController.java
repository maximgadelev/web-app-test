package com.gadelev.controller;

import com.gadelev.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class HelloController {
    private  final
    Service service ;
@Autowired
    public HelloController(Service service) {
        this.service = service;
    }


    @GetMapping("/find")
    @ResponseBody
    public String hello(@RequestParam Optional<String>city){
        String json = service.getByUrl("https://api.openweathermap.org/data/2.5/weather?q=" + city.orElse("Kazan") + "&appid=b684cfe1558a37f5cab1c97d60108160");
        if(json!=null){
            return json;
        }else{
            return "Sorry";
        }
    }
    @GetMapping("")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "sign_up";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
