package com.gadelev.controller;

import com.gadelev.dto.WeatherDto;
import com.gadelev.model.Request;
import com.gadelev.model.User;
import com.gadelev.model.Weather;
import com.gadelev.repo.UserRepository;
import com.gadelev.service.RequestService;
import com.gadelev.service.UserService;
import com.gadelev.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WeatherController {
    private final Service service;
    private final WeatherService weatherService;
    private final RequestService requestService;
    private final UserRepository userRepository;

    @Autowired
    public WeatherController(Service service,
                             WeatherService weatherService, RequestService requestService, UserService userService, UserRepository userRepository) {
        this.weatherService = weatherService;
        this.requestService = requestService;
        this.userRepository = userRepository;
        this.service = service;
    }
    @GetMapping("/weather")
    public String getWeatherJson(@RequestParam Optional<String> city, @RequestParam Optional<String> email) throws IOException {
        StringBuilder json = new StringBuilder(service.getByUrl("https://api.openweathermap.org/data/2.5/weather?q="
                + city.orElse("Kazan") + "&appid=b684cfe1558a37f5cab1c97d60108160"));

        if (email.isPresent()) {
            User user = userRepository.findByEmail(email.get());
            if (user == null) {
              return  email.get() + "null";
            }
            Map<String, Object> weatherParse = service.parseGson(json);
            Weather weather = new Weather(weatherParse.get("main humidity").toString(),city.orElse("Kazan"),email.get(), LocalDateTime.now());
            Request request = new Request(city.orElse("Kazan"),weather,user);
            requestService.save(request);
        }
        return json.toString();
    }
    @GetMapping("/allWeather")
    public List<WeatherDto> getAllWeatherSearch() throws IOException {
        return weatherService.findAll();
    }
    @GetMapping("/weather/city/{city}")
        public Iterable<WeatherDto> getAllWeatherByCity(@PathVariable String city){
            return weatherService.getAllByCity(city);
        }
}
