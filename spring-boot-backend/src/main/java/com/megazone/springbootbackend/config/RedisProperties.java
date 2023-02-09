package com.megazone.springbootbackend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.cache.redis.expire-time")
public class RedisProperties {
    private final Map<String, Long> ttl = new HashMap<>();
}
