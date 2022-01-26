package com.weather.service.api.integration.feign.openweathermap;

import com.weather.service.api.integration.feign.openweathermap.response.OpenWeatherMapCityWeatherResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface WeatherFeignClient {
    @GetMapping
    ResponseEntity<OpenWeatherMapCityWeatherResponseDTO> getWeatherByCityAndCountry(@RequestParam("q") String cityAndCountry,
                                                                                    @RequestParam("APPID") String apiKey);
    @GetMapping
    ResponseEntity<OpenWeatherMapCityWeatherResponseDTO> getWeatherByCityCoordinates(@RequestParam("lat") Double lat,
                                                                                     @RequestParam("lon") Double lon,
                                                                                    @RequestParam("APPID") String apiKey);

}
