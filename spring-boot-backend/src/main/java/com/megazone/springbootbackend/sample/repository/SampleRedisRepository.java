package com.megazone.springbootbackend.sample.repository;

import com.megazone.springbootbackend.sample.model.domain.SampleRedis;
import org.springframework.data.repository.CrudRepository;

public interface SampleRedisRepository extends CrudRepository<SampleRedis, String> {
}
