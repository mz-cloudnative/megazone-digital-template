package com.megazone.springbootbackend.sampleAPI.service;

import com.megazone.springbootbackend.sampleAPI.mapper.SampleMapper;
import com.megazone.springbootbackend.sampleAPI.mapper.SampleXmlMapper;
import com.megazone.springbootbackend.sampleAPI.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sampleAPI.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sampleAPI.model.entity.SampleJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
@Service("sampleMybatisService")
@RequiredArgsConstructor
public class SampleMybatisService {
    private final SampleMapper sampleMapper;

    private final SampleXmlMapper sampleXmlMapper;

    public void sampleDataAdd(SampleDataRequest param) {
        sampleMapper.save(SampleJpaEntity.requestToEntity(param));
    }
    public List<SampleDataResponse> sampleDataList() {
        return sampleMapper.findAll().stream()
                .map(SampleDataResponse::entityToResponse)
                .collect(Collectors.toList());
    }
    public SampleDataResponse sampleData(Long sampleId) {
        final var sampleData = sampleMapper.findById(sampleId).orElseThrow(NullPointerException::new);
        return SampleDataResponse.entityToResponse(sampleData);
    }
    public List<SampleDataResponse> getNames(String sampleName) {
        return sampleXmlMapper.findByName(sampleName).stream()
                .map(SampleDataResponse::entityToResponse)
                .collect(Collectors.toList());
    }
}
