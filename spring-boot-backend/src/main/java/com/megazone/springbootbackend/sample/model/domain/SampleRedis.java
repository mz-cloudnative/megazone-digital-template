package com.megazone.springbootbackend.sample.model.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("sampleRedis")
public class SampleRedis {

  @Id
  private Long sampleId;

  private String sampleName;

  @Builder
  public SampleRedis(Long sampleId, String sampleName) {
    this.sampleId = sampleId;
    this.sampleName = sampleName;
  }
}
