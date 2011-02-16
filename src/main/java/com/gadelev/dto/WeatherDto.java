package com.gadelev.dto;


import com.gadelev.model.Weather;
import java.time.LocalDateTime;

public class WeatherDto {
    private Integer id;



    private String humidity;


    private String city;

    private String email;

    private LocalDateTime time;


    public WeatherDto(Integer id, String humidity,String city, String email, LocalDateTime time) {
        this.id = id;
        this.humidity = humidity;
        this.city = city;
        this.email = email;
        this.time=time;
    }

    public static WeatherDto fromModel(Weather weather) {
        return new WeatherDto(weather.getId(),weather.getHumidity(), weather.getCity(), weather.getEmail(),weather.getTime());
    }
}