package com.weather.service.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("Merge array request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArrayMergeRequestDTO implements Serializable {
    private static final long serialVersionUID = -4860320197311079954L;
    @ApiParam("First array of the two arrays to be merged. e.g [1,3,5,7,8]")
    private Integer [] array1;
    @ApiParam("Second array of the two arrays to be merged. e.g [2,4,6,9,10]")
    private Integer [] array2;

}
