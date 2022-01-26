package com.weather.service.api.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Class responsible for manipulating the data related to a JSON object.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class JSONHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setVisibility(MAPPER.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        MAPPER.registerModule(new JavaTimeModule());
    }

    /**
     * Read a certain JSON file and map its value to a certain bean type.
     *
     * @param path The of the file to be read.
     * @param type The {@link JavaType} used for the conversion.
     * @return The representation of the file content as a bean.
     * @throws IOException ProductValidationError while reading the json file.
     */
    public static <T> T fileToBean(final String path, final Class<T> type) throws IOException {

        val file = new ClassPathResource(path).getFile();

        return MAPPER.readValue(file, TypeFactory.defaultInstance().constructType(type));
    }
}