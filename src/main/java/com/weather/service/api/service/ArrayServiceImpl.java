package com.weather.service.api.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class ArrayServiceImpl implements ArrayService{
    @Override
    public Integer[] mergeArrays(Integer[] array1, Integer[] array2) {
        if(array1.length == 0 ) {
            return array2;
        }
        if(array2.length == 0) {
            return array1;
        }
       return  Arrays.stream(Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).toArray())
               .sorted().toArray(Integer[]::new);

    }
}
