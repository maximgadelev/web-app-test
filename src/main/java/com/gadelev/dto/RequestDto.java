package com.gadelev.dto;

import com.gadelev.model.Request;

public class RequestDto {
    private Integer id;

    private String city;

    private WeatherDto weatherDto;

    public RequestDto(String city, WeatherDto weatherDto) {
        this.city = city;
        this.weatherDto = weatherDto;
    }

    public RequestDto(Integer id, String city, WeatherDto weatherDto) {
        this.id = id;
        this.city = city;
        this.weatherDto = weatherDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherDto getWeatherDto() {
        return weatherDto;
    }

    public void setWeatherDto(WeatherDto weatherDto) {
        this.weatherDto = weatherDto;
    }
public static RequestDto fromModel(Request request){
        return new RequestDto(request.getId(),request.getCity(),WeatherDto.fromModel(request.getWeather()));
}

}

