package com.megazone.springbootbackend.sampleAPI.service;

import com.megazone.springbootbackend.repository.SampleCustomRepository;
import com.megazone.springbootbackend.sampleAPI.model.domain.SampleData;
import com.megazone.springbootbackend.sampleAPI.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sampleAPI.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sampleAPI.model.entity.SampleJpaEntity;
import com.megazone.springbootbackend.sampleAPI.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("sampleService")
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

    public SampleData getData(String name) {
        return sampleCustomRepository.findAllByName(name);
    }
}
