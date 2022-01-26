package com.weather.service.api.controller.advice;

import com.weather.service.api.dto.ErrorResponse;
import com.weather.service.api.exception.WeatherException;
import com.weather.service.api.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice(basePackages = "com.weather.service.api.controller")
@Slf4j
public class ApiResponseHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<CustomFieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new CustomFieldError(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getRejectedValue())
                )
                .collect(toList());


        return new ResponseEntity(ResponseUtil.getResponseWithError(ErrorResponse.builder().reason(fieldErrors.toString()).errorStatusCode(1000).build()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleNoSuchElementException(Exception ex, NativeWebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity(ResponseUtil.getResponseWithError(ErrorResponse.builder().reason(ex.getMessage()).errorStatusCode(1000).build()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(WeatherException.class)
    public ResponseEntity<Object> handleUsageException(final WeatherException ex) {
        return new ResponseEntity<>(ResponseUtil.getResponseWithError(ErrorResponse.builder().reason(ex.getMessage()).errorStatusCode(1000).build()), HttpStatus.BAD_REQUEST);
    }

}

@Data
@AllArgsConstructor
class CustomFieldError {
    private static final long serialVersionUID = -3174832245402123024L;
    private String field;
    private String code;
    private Object rejectedValue;
}