package com.weather.service.api.controller;

import com.weather.service.api.dto.ArrayMergeRequestDTO;
import com.weather.service.api.integration.feign.openweathermap.response.OpenWeatherMapCityWeatherResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api")
public interface ArrayControllerApi {
    @ApiOperation(value = "Array merge api ", response = OpenWeatherMapCityWeatherResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Merged arrays"),
            @ApiResponse(code = 500, message = "Some error occurred while processing request")})
    @PostMapping(value = "/arrays/merge")
    ResponseEntity<Integer[]> mergeArrays(@ApiParam(value = "Array merge request")
                                          @RequestBody ArrayMergeRequestDTO arrayMergeRequestDTO);

}
