package com.megazone.springbootbackend.sample.service;

import com.megazone.springbootbackend.sample.repository.SampleCustomRepository;
import com.megazone.springbootbackend.sample.model.domain.SampleData;
import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import com.megazone.springbootbackend.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;
    private final SampleCustomRepository sampleCustomRepository;

    public void sampleDataAdd(SampleDataRequest param){
        sampleRepository.save(SampleJpaEntity.requestToEntity(param));
    }

    public List<SampleDataResponse> sampleDataList() {
        return sampleRepository.findAll().stream()
                .map(SampleDataResponse::entityToResponse)
                .collect(Collectors.toList());
    }

    public SampleDataResponse sampleData(Long sampleId) {
        final var sampleData = sampleRepository.findById(sampleId).orElseThrow(NullPointerException::new);
        return SampleDataResponse.entityToResponse(sampleData);
    }

    public SampleDataResponse getData(String name) {
        final var sampleData = sampleCustomRepository.findAllByName(name);
        return SampleDataResponse.domainToResponse(sampleData);
    }
}
