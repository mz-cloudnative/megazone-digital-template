package com.megazone.springbootbackend.sample.service;

import com.megazone.springbootbackend.sample.repository.mapper.SampleMapper;
import com.megazone.springbootbackend.sample.repository.mapper.SampleXmlMapper;
import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : Mybatis 예제</li>
 * <li>서브 업무명 : </li>
 * <li>설       명 : </li>
 * <li>작   성  자 : mz01-ohyunbk</li>
 * <li>작   성  일 : 2023/01/16 12:48 PM</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * </pre>
 ***************************************************/

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleMybatisService {
    private final SampleMapper sampleMapper;

    private final SampleXmlMapper sampleXmlMapper;

    public void sampleDataAdd(SampleDataRequest param) {
        sampleMapper.save(SampleJpaEntity.requestToEntity(param));
    }

    @Transactional(readOnly = true)
    public List<SampleDataResponse> sampleDataList() {
        return sampleMapper.findAll().stream()
                .map(SampleDataResponse::entityToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SampleDataResponse sampleData(Long sampleId) {
        final var sampleData = sampleMapper.findById(sampleId).orElseThrow(NullPointerException::new);
        return SampleDataResponse.entityToResponse(sampleData);
    }

    @Transactional(readOnly = true)
    public List<SampleDataResponse> getNames(String sampleName) {
        return sampleXmlMapper.findByName(sampleName).stream()
                .map(SampleDataResponse::entityToResponse)
                .collect(Collectors.toList());
    }
}
