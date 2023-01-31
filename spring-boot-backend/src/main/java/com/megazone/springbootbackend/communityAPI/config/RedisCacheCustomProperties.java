package com.megazone.springbootbackend.communityAPI.config;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : TTL 설정</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : CacheProperties</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/01/19</li>
 * <li>설     명 : yml TTL 설정 가져오기 위함.</li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/01/19 10:38 AM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Getter @Setter
@ConfigurationProperties(prefix = "spring.cache.redis.expire-time")
public class RedisCacheCustomProperties {
  private final Map<String, Long> ttl = new HashMap<>();
}
