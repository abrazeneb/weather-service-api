package com.weather.service.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityWeatherResponseDTO extends WeatherResponse{

    private static final long serialVersionUID = 2965599859245795624L;
    private CoordinateDTO coord;
    private List<WeatherDTO> weather;
    private String base;
    private MainDTO main;
    private Integer visibility;
    private WindDTO wind;
    private CloudDTO clouds;
    private Long dt;
    private SysDTO sys;
    private Long timezone;
    private Long id;
    private String name;
    private Integer cod;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CoordinateDTO implements Serializable {
        private static final long serialVersionUID = 4192121766565818261L;
        private Double lon;
        private Double lat;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class WeatherDTO implements Serializable {
        private static final long serialVersionUID = 3772865592002025106L;
        private Integer id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MainDTO implements Serializable {
        private static final long serialVersionUID = 3005262412324257940L;
        private Double temp;
        private Double feelsLike;
        private Double tempMin;
        private Double tempMax;
        private Integer pressure;
        private Integer humidity;
        private Integer seaLevel;
        private Integer grndLevel;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class WindDTO implements Serializable {

        private static final long serialVersionUID = -7983247803954520538L;
        private Double speed;
        private Double gust;
        private Integer deg;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CloudDTO implements Serializable {

        private static final long serialVersionUID = -6670270476977942251L;
        private Integer all;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SysDTO implements Serializable {

        private static final long serialVersionUID = 8418397822707249159L;
        private Integer type;
        private Long id;
        private String country;
        private Long sunrise;
        private Long sunset;
    }
}
