package com.megazone.springbootbackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@RequiredArgsConstructor
@Configuration
public class CacheConfig {
    private final RedisConnectionFactory redisConnectionFactory;
    private final RedisProperties redisProperties;
    // Cache 이용을 하기 위함. 어노테이션을 이용해서 cache 설정이 가능하다.
    @Bean
    public RedisCacheManager redisCacheManager() {
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfiguration())
                .withInitialCacheConfigurations(configurationMap())
                .build();
    }

    // 기본적인 redis cache 설정
    private RedisCacheConfiguration defaultCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues() // null 값 캐싱하지 않기
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) // key serializer 변경
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer())) // value serializer 변경
                .entryTtl(Duration.ofMinutes(1L));
    }

    // key name의 prefix에 따른 ttl 설정
    private Map<String, RedisCacheConfiguration> configurationMap() {
        Map<String, RedisCacheConfiguration> customConfigurationMap = new HashMap<>();
        for(Entry<String, Long> entry:redisProperties.getTtl().entrySet()) {
            customConfigurationMap.put(entry.getKey(), defaultCacheConfiguration().entryTtl(Duration.ofSeconds(entry.getValue())));
        }
        return customConfigurationMap;
    }

    // 객체의 직렬화/역직렬화에서 타입에 따른 형식 설정
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // timestamp 형식은 따르지 않도록 설정
        objectMapper.registerModules(new JavaTimeModule(), new Jdk8Module()); // LocalDateTime 매핑을 위해 모듈 활성화

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

    /*
        StringRedisSerializer : binary 데이터로 저장되기 때문에 이를 String으로 변환(반대도 가능)하며 UTF-8 인코딩 방식을 사용한다.

        GenericJackson2JsonRedisSerializer : 객체를 json타입으로 직렬화/역직렬화를 수행한다.
     */

    /*
        Lettuce : Multi-Thread에서 Thread-Safe한 Redis 클라이언트로 netty에 의해 관리된다.
                  고급 기능(Sentinel, Cluster 등)을 지원하고 비동기 방식으로 요청한다.
                  TPS/CPU/Connection 개수와 응답속도 등이 Jedis보다 뛰어나다.

        Jedis : Multi-Thread에서 Thread-Unsafe하며 Connection Pool을 이용해 멀티쓰레드 환경을 구축한다.
                Jedis 인스턴스와 연결할 때마다 Connection Pool을 불러오고 스레드 갯수가 늘어난다면 시간이 상당히 소요될 수 있다.
     */
}
