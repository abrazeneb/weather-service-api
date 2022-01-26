package com.weather.service.api.mapper;

import ma.glasnost.orika.MapperFactory;

@FunctionalInterface
public interface ConfigurableMapperConfigurer {
    /**
     * Provide custom configurations to the Orika MapperFactory used by this mapper.
     */
    void configure(MapperFactory factory);
}
