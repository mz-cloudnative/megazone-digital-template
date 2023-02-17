package com.megazone.springbootbackend.sample.service.impl;

import com.megazone.springbootbackend.sample.model.domain.SampleRedis;
import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sample.repository.SampleRedisRepository;
import com.megazone.springbootbackend.sample.repository.mapper.RedisSampleMapper;
import com.megazone.springbootbackend.sample.service.SampleRedisService;
import javax.management.openmbean.KeyAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleRedisServiceImpl implements SampleRedisService {

  private final SampleRedisRepository sampleRedisRepository;
  private final RedisSampleMapper redisSampleMapper;

  @Override
  public SampleDataResponse getRedisSample(String name) {
    return redisSampleMapper.sampleRedisToResponse(
        sampleRedisRepository.findBySampleName(name)
            .orElseThrow(() -> new NullPointerException("존재하지 않습니다."))
    );
  }

  @Override
  public void save(SampleDataRequest param, Long... expiration) {
    //Redis 존재 여부 체크
    if (sampleRedisRepository.existsById(param.getSampleId().toString())) {
      throw new KeyAlreadyExistsException("이미 존재하는 Redis 입니다.");
    }
    sampleRedisRepository.save(
        new SampleRedis(param.getSampleId(), param.getSampleName(), expiration));
  }

  @Override
  public void delete(SampleRedis sampleRedis) {
    sampleRedisRepository.delete(sampleRedis);
  }
}
