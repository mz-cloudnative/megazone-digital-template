package com.megazone.springbootbackend.sample.repository;

import com.megazone.springbootbackend.sample.model.domain.SampleRedis;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface SampleRedisRepository extends CrudRepository<SampleRedis, String> {
  Optional<SampleRedis> findBySampleName(String name);
}
