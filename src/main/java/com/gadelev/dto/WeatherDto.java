package com.gadelev.dto;


import com.gadelev.model.Weather;

import java.time.LocalDateTime;

public class WeatherDto {
    private Integer id;
    private String humidity;

    private String city;
    private String email;

    private LocalDateTime time;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public WeatherDto(Integer id, String humidity, String city, String email, LocalDateTime time) {
        this.id = id;
        this.humidity = humidity;
        this.city = city;
        this.email = email;
        this.time = time;
    }

    public static WeatherDto fromModel(Weather weather) {
        return new WeatherDto(weather.getId(), weather.getHumidity(), weather.getCity(), weather.getEmail(), weather.getTime());
    }
}