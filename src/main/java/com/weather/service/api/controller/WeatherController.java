package com.weather.service.api.controller;

import com.weather.service.api.dto.CityWeatherResponseDTO;
import com.weather.service.api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherControllerApi{

    private final WeatherService weatherService;

    @Override
    public ResponseEntity<CityWeatherResponseDTO> getWeather(String cityName, String countryCode, String appKey) {
        return ResponseEntity.ok(weatherService.getWeather(cityName, countryCode, appKey));
    }

    @Override
    public ResponseEntity<CityWeatherResponseDTO> getWeatherByCoordinates(Double lat, Double lon, String appKey) {
        return ResponseEntity.ok(weatherService.getWeather(lat, lon, appKey));
    }
}
