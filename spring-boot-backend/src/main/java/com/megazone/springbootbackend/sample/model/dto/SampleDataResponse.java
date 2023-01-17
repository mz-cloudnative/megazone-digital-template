package com.megazone.springbootbackend.sample.model.dto;


import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SampleDataResponse {
    @Schema(description = "샘플아이디")
    private Long sampleId;

    @Schema(description = "샘플이름")
    private String sampleName;

    @Schema(description = "샘플등록시간")
    private LocalDateTime sampleRegDtt;

    public static SampleDataResponse entityToResponse(SampleJpaEntity param){
        return SampleDataResponse.builder()
                .sampleId(param.getSampleId())
                .sampleName(param.getSampleName())
                .sampleRegDtt(param.getSampleRegDtt())
                .build();
    }
}
