package com.weather.service.api.service;

import com.weather.service.api.dto.CityWeatherResponseDTO;
import com.weather.service.api.integration.feign.openweathermap.WeatherFeignClientApi;
import com.weather.service.api.integration.feign.openweathermap.response.OpenWeatherMapCityWeatherResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements  WeatherService{

    private final MapperFacade mapper;
    private final WeatherFeignClientApi weatherFeignClientApi;

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(cacheNames = "city-weather", key = "#cityName", unless = "#result == null")
    public CityWeatherResponseDTO getWeather(final String cityName, final String countryCode, final String apiKey) {
        log.debug("Retrieving weather information for the city of {} in {}", cityName, countryCode);
        final ResponseEntity<OpenWeatherMapCityWeatherResponseDTO> response =
                weatherFeignClientApi.getWeatherByCityAndCountry((new StringBuilder()).append(cityName).append(",").append(countryCode).toString(), apiKey);
        log.debug("Received response {} from OpenWeatherMap", response);
        return mapper.map(response.getBody(), CityWeatherResponseDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(cacheNames = "city-weather", key = "#lat-#lon", unless = "#result == null")
    public CityWeatherResponseDTO getWeather(final Double lat, final Double lon, final String apiKey) {
        log.debug("Retrieving weather information for latitude of {} longitude {}", lat, lon);
        final ResponseEntity<OpenWeatherMapCityWeatherResponseDTO> response = weatherFeignClientApi.getWeatherByCityCoordinates(lat, lon, apiKey);
        log.debug("Received response {} from OpenWeatherMap", response);
        return mapper.map(response.getBody(), CityWeatherResponseDTO.class);
    }
}
