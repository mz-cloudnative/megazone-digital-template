package com.megazone.springbootbackend.sample.model.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("sample")
public class SampleRedis {
  @Id
  private Long sampleId;

  @Indexed
  private String sampleName;

  private LocalDateTime sampleRegDtt;

  @TimeToLive
  private Long expiration; //초

  /**
   * @apiNote SampleRedis 생성자. 만료시간입력 여부에 따라 분기 처리.
   *
   * @since 2023/01/27 11:07 AM
   * @author mz01-ohyunbk
   */
  public SampleRedis(Long sampleId, String sampleName, Long... expiration) {
    this.sampleId = sampleId;
    this.sampleName = sampleName;
    this.sampleRegDtt = LocalDateTime.now();
    this.expiration = Arrays.stream(expiration).findFirst().orElse(30L);
  }
}
