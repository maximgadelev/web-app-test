package com.gadelev.service;

import com.gadelev.dto.WeatherDto;
import com.gadelev.model.Weather;
import java.util.List;

public interface WeatherService {
    List<WeatherDto> getAllByCity(String city);
    List<WeatherDto> findAll();
}
