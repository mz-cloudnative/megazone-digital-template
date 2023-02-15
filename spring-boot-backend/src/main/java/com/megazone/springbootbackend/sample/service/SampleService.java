package com.megazone.springbootbackend.sample.service;

import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import com.megazone.springbootbackend.sample.repository.SampleCustomRepository;
import com.megazone.springbootbackend.sample.repository.SampleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SampleService {

  private final SampleRepository sampleRepository;
  private final SampleCustomRepository sampleCustomRepository;

  public void sampleDataAdd(SampleDataRequest param) {
    sampleRepository.save(SampleJpaEntity.requestToEntity(param));
  }

  @Transactional(readOnly = true)
  public List<SampleDataResponse> sampleDataList() {
    return sampleRepository.findAll().stream()
        .map(SampleDataResponse::entityToResponse)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public SampleDataResponse sampleData(Long sampleId) {
    final var sampleData = sampleRepository.findById(sampleId)
        .orElseThrow(NullPointerException::new);
    return SampleDataResponse.entityToResponse(sampleData);
  }

  @Transactional(readOnly = true)
  public SampleDataResponse getData(String name) {
    final var sampleData = sampleCustomRepository.findAllByName(name);
    return SampleDataResponse.domainToResponse(sampleData);
  }

  @Transactional(readOnly = true)
  public SampleDataResponse sampleRedisData(String name) {
    final var sampleData = sampleCustomRepository.findAllByName(name);
    return SampleDataResponse.domainToResponse(sampleData);
  }

}
