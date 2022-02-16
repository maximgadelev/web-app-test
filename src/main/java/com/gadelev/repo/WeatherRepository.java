package com.gadelev.repo;
import com.gadelev.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}