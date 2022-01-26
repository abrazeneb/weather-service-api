package com.weather.service.api.service;

import com.weather.service.api.integration.feign.openweathermap.WeatherFeignClientApi;
import com.weather.service.api.integration.feign.openweathermap.response.OpenWeatherMapCityWeatherResponseDTO;
import com.weather.service.api.mapper.FacadeServiceMapper;
import com.weather.service.api.mapper.configurers.OpenWeatherMapConfigurer;
import com.weather.service.api.util.JSONHelper;
import lombok.SneakyThrows;
import lombok.val;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeatherServiceTest.MapperTestConfig.class)
public class WeatherServiceTest {

    @ComponentScan(basePackageClasses = {FacadeServiceMapper.class, OpenWeatherMapConfigurer.class})
    public static class MapperTestConfig {
    }

    @Autowired
    private MapperFacade mapper;

    @MockBean
    private WeatherFeignClientApi weatherFeignClientApi;

    private WeatherService serviceUnderTest;

    @BeforeEach
    public void setup() {
        serviceUnderTest = new WeatherServiceImpl(mapper, weatherFeignClientApi);
    }

    @SneakyThrows
    @Test
    public void getWeather_withValidRequest_shouldPass() {
        // given
        val city = "city";
        val countryCode = "countryCode";
        val appKey = "appKey";

        String requestFullPath = "/test-cases/weather_response.json";
        OpenWeatherMapCityWeatherResponseDTO weatherResponseDTO = JSONHelper.fileToBean(requestFullPath,OpenWeatherMapCityWeatherResponseDTO.class);

        // when
        when(weatherFeignClientApi.getWeatherByCityAndCountry(any(), any())).thenReturn(ResponseEntity.ok(weatherResponseDTO));

        //then
        val actual = serviceUnderTest.getWeather(city, countryCode, appKey);

        assertEquals(weatherResponseDTO.getBase(), actual.getBase());
    }


    @SneakyThrows
    @Test
    public void getWeather_withValidLatAndLonRequest_shouldPass() {
        // given
        val lat = 25.2048;
        val lon = 55.2708;
        val appKey = "appKey";

        String requestFullPath = "/test-cases/weather_response.json";
        OpenWeatherMapCityWeatherResponseDTO weatherResponseDTO = JSONHelper.fileToBean(requestFullPath,OpenWeatherMapCityWeatherResponseDTO.class);

        // when
        when(weatherFeignClientApi.getWeatherByCityCoordinates(lat, lon, appKey)).thenReturn(ResponseEntity.ok(weatherResponseDTO));

        //then
        val actual = serviceUnderTest.getWeather(lat, lon, appKey);

        assertEquals(weatherResponseDTO.getBase(), actual.getBase());
        assertEquals(weatherResponseDTO.getWeather().size(), actual.getWeather().size());
    }
}
