package com.gadelev.service;

import com.gadelev.dto.WeatherDto;
import com.gadelev.model.Weather;
import com.gadelev.repo.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class WeatherServiceImpl implements WeatherService{
    private final WeatherRepository weatherRepository;
@Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public List<WeatherDto> getAllByCity(String city) {
        return weatherRepository.getAllByCity(city);
    }

    @Override
    public List<WeatherDto> findAll() {
        return weatherRepository.findAll().stream().map(weather -> new WeatherDto(
                weather.getId(),
                weather.getHumidity(),
                weather.getCity(),
                weather.getTime()
        )).collect(Collectors.toList());
    }
}
