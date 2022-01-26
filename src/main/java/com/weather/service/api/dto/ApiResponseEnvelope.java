package com.weather.service.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseEnvelope<WeatherResponse> implements Serializable {
    private static final long serialVersionUID = 2020637068211878392L;

    @ApiModelProperty(position = 1, value = "Payload", required = true)
    private WeatherResponse payload;
    @ApiModelProperty(position = 2, value = "Time of api response", required = true, example = "2019-08-28T10:09:26.595Z")
    private Instant instant;

    private String status;

    private ErrorResponse error;
}
