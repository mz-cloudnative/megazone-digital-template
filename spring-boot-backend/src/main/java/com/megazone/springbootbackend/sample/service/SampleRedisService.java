package com.megazone.springbootbackend.sample.service;

import com.megazone.springbootbackend.sample.model.domain.SampleRedis;
import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;

public interface SampleRedisService {

  SampleDataResponse getRedisSample(String name);
  void save(SampleDataRequest param, Long... expiration);
  void delete(SampleRedis sampleRedis);
}
