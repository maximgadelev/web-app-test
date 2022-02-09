package com.gadelev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloController {
    Service service = new Service();
    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String>city){
        String json = service.getByUrl("https://api.openweathermap.org/data/2.5/weather?q=" + city.orElse("Kazan") + "&appid=b684cfe1558a37f5cab1c97d60108160");
        if(json!=null){
            return json;
        }else{
            return "Sorry";
        }
    }
}
