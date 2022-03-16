package com.gadelev.dto;


import com.gadelev.model.Weather;

import java.time.LocalDateTime;

public class WeatherDto {
    private Integer id;
    private String humidity;

    private String city;


    private LocalDateTime time;

    public WeatherDto(Integer id, String humidity, String city, LocalDateTime time) {
        this.id = id;
        this.humidity = humidity;
        this.city = city;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }



    public static WeatherDto fromModel(Weather weather) {
        return new WeatherDto(weather.getId(), weather.getHumidity(), weather.getCity(), weather.getTime());
    }
}