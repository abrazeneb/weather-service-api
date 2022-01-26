package com.weather.service.api.config.cache;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cache", ignoreUnknownFields = false)
public class CachingProperties {
    private EhcacheProps ehcache;

    @Getter
    @Setter
    public static class EhcacheProps {
        private long defaultMaxEntriesSize = 50;
        private long defaultTimeToLiveSeconds = 60;
    }

}
