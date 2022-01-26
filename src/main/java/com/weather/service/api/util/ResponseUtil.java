package com.weather.service.api.util;

import com.weather.service.api.dto.ApiResponseEnvelope;
import com.weather.service.api.dto.ErrorResponse;
import com.weather.service.api.dto.Status;
import com.weather.service.api.dto.WeatherResponse;
import lombok.experimental.UtilityClass;

import java.time.Instant;

@UtilityClass
public class ResponseUtil {

    public <T extends WeatherResponse> ApiResponseEnvelope<T> getResponse(T t) {
        ApiResponseEnvelope<T> apiResponseeEnvelope = new ApiResponseEnvelope<>();
        apiResponseeEnvelope.setPayload(t);
        apiResponseeEnvelope.setInstant(Instant.now());
        apiResponseeEnvelope.setStatus(Status.OK.name());
        return apiResponseeEnvelope;
    }

    public ApiResponseEnvelope getResponseWithError(ErrorResponse errorResponse) {
        ApiResponseEnvelope apiResponseEnvelope = new ApiResponseEnvelope<>();
        apiResponseEnvelope.setInstant(Instant.now());
        apiResponseEnvelope.setError(errorResponse);
        apiResponseEnvelope.setStatus(Status.ERROR.name());
        return apiResponseEnvelope;
    }
}
