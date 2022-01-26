package com.weather.service.api.config.cache;

import lombok.RequiredArgsConstructor;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class EhCachingConfig {

    @Value("${cache.ehcache.defaultMaxEntriesSize}")
    private Integer defaultMaxEntriesSize;

    @Value("${cache.ehcache.defaultTimeToLiveSeconds}")
    private Integer defaultTimeToLiveSeconds;

    private final CachingProperties properties;

    private Map<String, CacheConfiguration<?, ?>> createCacheConfigurations() {
        Map<String, org.ehcache.config.CacheConfiguration<?, ?>> caches = new HashMap<>();
        caches.put("default-caches",  this.getDefaultCacheConfiguration());
        caches.put("city-weather", getCacheConfiguration(defaultMaxEntriesSize, defaultTimeToLiveSeconds));
        return caches;
    }

    private CacheConfiguration<Object, Object> getDefaultCacheConfiguration() {
        return getCacheConfiguration(properties.getEhcache().getDefaultMaxEntriesSize(), properties.getEhcache().getDefaultTimeToLiveSeconds());
    }

    private CacheConfiguration<Object, Object> getCacheConfiguration(long maxEntriesSize, long timeToLiveSeconds ) {
        return CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(maxEntriesSize))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(timeToLiveSeconds)))
                .build();
    }

    @Bean
    public org.springframework.cache.CacheManager cacheManager() {
        EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider();
        DefaultConfiguration configuration = new DefaultConfiguration(createCacheConfigurations(), provider.getDefaultClassLoader());
        return new JCacheCacheManager(provider.getCacheManager(provider.getDefaultURI(), configuration));
    }
}
