package com.weather.service.api.integration.feign.openweathermap;

import com.weather.service.api.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${feign.weather-client:WeatherFeignClient}", url = "${feign.weather-client.url:https://api.openweathermap.org/data/2.5/weather}", configuration = FeignConfiguration.class)
public interface WeatherFeignClientApi extends WeatherFeignClient{
}
