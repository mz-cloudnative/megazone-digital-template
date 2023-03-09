package com.megazone.springbootbackend.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : Redis 설정</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : CacheConfig</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/01/19</li>
 * <li>설     명 : yml설정만으로도 사용이 가능하나 커스텀을 위해 필요함.</li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/01/19 10:27 AM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@RequiredArgsConstructor
@EnableCaching
@Configuration
@EnableConfigurationProperties(value = {CacheProperties.class, RedisCacheCustomProperties.class})
@EnableRedisRepositories
public class RedisCacheConfig {

  private final CacheProperties cacheProperties;
  private final RedisCacheCustomProperties redisCacheCustomProperties;

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(host, port);
  }

  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // timestamp 형식 안따르도록 설정
    mapper.registerModules(new JavaTimeModule(), new Jdk8Module()); // LocalDateTime 매핑을 위해 모듈 활성화
    return mapper;
  }

  private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
        Object.class);
    ObjectMapper objectMapper = objectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    return jackson2JsonRedisSerializer;
  }

  private RedisCacheConfiguration redisCacheDefaultConfiguration() {
    return RedisCacheConfiguration
        .defaultCacheConfig()
        .disableCachingNullValues() //Null 값 캐싱하지 않기
        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
            new StringRedisSerializer())) // key Serializer 변경
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
            jackson2JsonRedisSerializer()))// Value Serializer 변경
        .entryTtl(cacheProperties.getRedis().getTimeToLive()); //기본 유효시간
  }

  private Map<String, RedisCacheConfiguration> redisCacheConfigurationMap() {
    Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
    for (Entry<String, Long> cacheNameAndTimeout : redisCacheCustomProperties.getTtl().entrySet()) {
      cacheConfigurations
          .put(cacheNameAndTimeout.getKey(), redisCacheDefaultConfiguration().entryTtl(
              Duration.ofSeconds(cacheNameAndTimeout.getValue()))); //커스텀 유효시간
    }
    return cacheConfigurations;
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    //@Cacheable @CacheEvict @CachePut 어노테이션으로 캐싱 처리할 때 사용되는 설정
    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory)
        .cacheDefaults(redisCacheDefaultConfiguration())
        .withInitialCacheConfigurations(redisCacheConfigurationMap())
        .build();
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    //Redis JPA, RedisTemplate으로 캐싱처리 할 때 사용되는 설정
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
    redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
