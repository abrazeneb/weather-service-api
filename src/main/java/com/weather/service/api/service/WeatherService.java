package com.weather.service.api.service;

import com.weather.service.api.dto.CityWeatherResponseDTO;

public interface WeatherService {
    /**
     * Fetch weather data for the given city and country
     * @param cityName
     * @param countryCode
     * @param apiKey
     * @return
     */
    CityWeatherResponseDTO getWeather(final String cityName, final String countryCode, final String apiKey);

    /**
     *Fetch weather data for the given latitude and longitude
     * @param lat
     * @param lon
     * @param apiKey
     * @return
     */
    CityWeatherResponseDTO getWeather(final Double lat, final Double lon, final String apiKey);
}
