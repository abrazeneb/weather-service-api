package com.weather.service.api.controller;


import com.weather.service.api.dto.CityWeatherResponseDTO;
import com.weather.service.api.integration.feign.openweathermap.response.OpenWeatherMapCityWeatherResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/api")
public interface WeatherControllerApi {

    @ApiOperation(value = "Weather Api ", response = OpenWeatherMapCityWeatherResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved Weather details for the city"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping(value = "/city/weather")
    ResponseEntity<CityWeatherResponseDTO> getWeather(@ApiParam(value = "City name", example = "Dubai")
                                               @RequestParam(value = "city") final String cityName,
                                                      @ApiParam(value = "ISO 3166 country code", example = "AE")
                                               @RequestParam(value = "country") final String countryCode,
                                                      @ApiParam(value = "Your unique App key ( For openweathermap, you can always find it on your account page under the \"API key\" tab)", example = "ffa6f13ea40a22452bba3be726315d3f")
                                               @RequestParam(value = "appKey", required = false, defaultValue = "f630355baf1c53420207fbd7621093d2") final String appKey);

    @ApiOperation(value = "Location Weather Api ", response = OpenWeatherMapCityWeatherResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved Weather details for the location"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping(value = "/location/weather")
    ResponseEntity<CityWeatherResponseDTO> getWeatherByCoordinates(@ApiParam(value = "Latitude", example = "25.2048")
                                               @RequestParam(value = "lat") final Double lat,
                                                      @ApiParam(value = "longitude", example = "55.2708")
                                               @RequestParam(value = "lon") final Double lon,
                                                      @ApiParam(value = "Your unique App key ( For openweathermap, you can always find it on your account page under the \"API key\" tab)", example = "ffa6f13ea40a22452bba3be726315d3f")
                                               @RequestParam(value = "appKey", required = false, defaultValue = "f630355baf1c53420207fbd7621093d2") final String appKey);
}
