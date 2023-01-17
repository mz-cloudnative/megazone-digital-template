package com.megazone.springbootbackend.sample.repository;

import com.megazone.springbootbackend.sample.model.domain.SampleData;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleCustomRepository {
  SampleData findAllByName(String name);
}
