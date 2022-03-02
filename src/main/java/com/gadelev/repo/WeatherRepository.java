package com.gadelev.repo;
import com.gadelev.dto.WeatherDto;
import com.gadelev.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<WeatherDto> getAllByCity(String city);
}