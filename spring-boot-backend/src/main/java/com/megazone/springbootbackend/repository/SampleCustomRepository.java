package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.sampleAPI.model.domain.SampleData;

public interface SampleCustomRepository {

  SampleData findAllByName(String name);
}
