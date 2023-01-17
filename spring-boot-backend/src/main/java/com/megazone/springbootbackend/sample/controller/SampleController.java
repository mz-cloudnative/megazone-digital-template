package com.megazone.springbootbackend.sample.controller;

import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sample.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
@Slf4j
public class SampleController {

    private final SampleService sampleService;

    /**
     * 1. Sample Insert API - [API01] Sample Insert
     * 샘플 등록 API
     * @param request
     * @return
     */
    @Operation(summary = "[API01] Sample Insert API ", description = "샘플 등록 API")
    @PostMapping(value = "/sample-add", produces = "application/json")
    public String sampleAdd(
            @Parameter(description = "샘플 data insert", required = true)
            @RequestBody final SampleDataRequest param){
        sampleService.sampleDataAdd(param);
        return "200 성공";
    }

    /**
     * 2. Sample Select List API - [API02] Sample Select List
     * 샘플 목록 조회 API
     * @param
     * @return
     */
    @Operation(summary = "[API02] Sample Select List ", description = "샘플 목록 조회 API")
    @GetMapping(value = "/sample-list", produces = "application/json")
    public List<SampleDataResponse> sampleList(){
        final var sampleList = sampleService.sampleDataList();
        return sampleList;
    }

    /**
     * 3. Sample Select One API - [API03] Sample Select One
     * 샘플 단건 조회 API
     * @param
     * @return
     */
    @Operation(summary = "[API03] Sample Select One ", description = "샘플 단건 조회 API / 데이터 없으면 NPE 발생!!!!!!!! 오류가 발생 할 것이야~")
    @GetMapping(value = "/sample-one/{sampleId}", produces = "application/json")
    public SampleDataResponse sampleOne(
            @Parameter(description = "샘플번호")
            @PathVariable(value = "sampleId")
            @NotNull final Long sampleId){
        final var sampleData = sampleService.sampleData(sampleId);
        return sampleData;
    }

    @Operation(summary = "[API04] Sample Select One ", description = "QuerlDSL 샘플")
    @GetMapping("/name-detail")
    public @ResponseBody SampleDataResponse getTestName(@RequestParam("name") String name) {
        return sampleService.getData(name);
    }
}
