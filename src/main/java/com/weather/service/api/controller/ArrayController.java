package com.weather.service.api.controller;

import com.weather.service.api.dto.ArrayMergeRequestDTO;
import com.weather.service.api.service.ArrayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArrayController implements  ArrayControllerApi{

    private final ArrayService arrayService;

    @Override
    public ResponseEntity<Integer[]> mergeArrays(ArrayMergeRequestDTO arrayMergeRequestDTO) {
       log.debug("Received merge request {}", arrayMergeRequestDTO);
        return ResponseEntity.ok(arrayService.mergeArrays(arrayMergeRequestDTO.getArray1(), arrayMergeRequestDTO.getArray2()));
    }
}
