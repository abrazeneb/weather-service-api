package com.weather.service.api.mapper.configurers;

import com.weather.service.api.dto.CityWeatherResponseDTO;
import com.weather.service.api.integration.feign.openweathermap.response.OpenWeatherMapCityWeatherResponseDTO;
import com.weather.service.api.mapper.ConfigurableMapperConfigurer;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenWeatherMapConfigurer implements ConfigurableMapperConfigurer {
    @Override
    public void configure(MapperFactory factory) {

        factory.classMap(OpenWeatherMapCityWeatherResponseDTO.class, CityWeatherResponseDTO.class)
                .customize(new CustomMapper<OpenWeatherMapCityWeatherResponseDTO, CityWeatherResponseDTO>() {
                    @Override
                    public void mapAtoB(OpenWeatherMapCityWeatherResponseDTO source, CityWeatherResponseDTO destination, MappingContext context) {
                        destination.setBase(source.getBase());
                        destination.setClouds(mapperFacade.map(source.getClouds(), CityWeatherResponseDTO.CloudDTO.class));
                        destination.setCod(source.getCod());
                        destination.setWeather(mapperFacade.mapAsList(source.getWeather(), CityWeatherResponseDTO.WeatherDTO.class));
                        destination.setCoord(mapperFacade.map(source.getCoord(), CityWeatherResponseDTO.CoordinateDTO.class));
                        destination.setMain(mapperFacade.map(source.getMain(), CityWeatherResponseDTO.MainDTO.class));
                        destination.setWind(mapperFacade.map(source.getWind(), CityWeatherResponseDTO.WindDTO.class));
                        destination.setDt(source.getDt());
                        destination.setSys(mapperFacade.map(source.getSys(), CityWeatherResponseDTO.SysDTO.class));
                        destination.setTimezone(source.getTimezone());
                        destination.setId(source.getId());
                        destination.setName(source.getName());
                        destination.setVisibility(source.getVisibility());

                    }
                }).register();

        factory.classMap(OpenWeatherMapCityWeatherResponseDTO.SysDTO.class, CityWeatherResponseDTO.SysDTO.class)
                .customize(new CustomMapper<OpenWeatherMapCityWeatherResponseDTO.SysDTO, CityWeatherResponseDTO.SysDTO>() {
                    @Override
                    public void mapAtoB(OpenWeatherMapCityWeatherResponseDTO.SysDTO source, CityWeatherResponseDTO.SysDTO destination, MappingContext context) {

                        destination.setCountry(source.getCountry());
                        destination.setType(source.getType());
                        destination.setId(source.getId());
                        destination.setSunrise(source.getSunrise());
                        destination.setSunset(source.getSunset());
                    }
                }).register();
    }
}
