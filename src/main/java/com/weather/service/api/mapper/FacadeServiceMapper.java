package com.weather.service.api.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FacadeServiceMapper extends ConfigurableMapper {
    private final List<ConfigurableMapperConfigurer> configurers = new ArrayList<>();

    @Autowired
    public FacadeServiceMapper(final List<ConfigurableMapperConfigurer> configurers) {
        super(false);
        this.configurers.addAll(configurers);
        init();
    }

    @Override
    protected void configure(final MapperFactory factory) {
        configurers.forEach(configurer -> configurer.configure(factory));
    }
}
