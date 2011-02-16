package com.gadelev.controller;

import com.gadelev.dto.UserDto;
import com.gadelev.dto.WeatherDto;
import com.gadelev.model.User;
import com.gadelev.model.Weather;
import com.gadelev.repo.UserRepository;
import com.gadelev.repo.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WeatherController {
    private final Service service;
    private final WeatherRepository weatherRepository;
    private final UserRepository userRepository;

    @Autowired
    public WeatherController(Service service, WeatherRepository weatherRepository,
                             UserRepository userRepository) {
        this.userRepository = userRepository;
        this.service = service;
        this.weatherRepository = weatherRepository;
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
            weatherRepository.save(new Weather(weatherParse.get("main humidity").toString(),city.orElse("Kazan"),email.get(), LocalDateTime.now()));
        }
        return email.orElse(null) + "124";
    }
    @GetMapping("/allWeather")
    public List<WeatherDto> getAllWeatherSearch() throws IOException {
        return weatherRepository.findAll().stream().map(weather -> new WeatherDto(
                weather.getId(),
                weather.getHumidity(),
                weather.getCity(),
                weather.getEmail(),
                weather.getTime()
        )).collect(Collectors.toList());
    }

}
