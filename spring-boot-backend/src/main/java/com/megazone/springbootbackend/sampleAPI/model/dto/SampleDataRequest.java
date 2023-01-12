package com.megazone.springbootbackend.sampleAPI.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SampleDataRequest {
    @Schema(description = "샘플아이디")
    private Long sampleId;

    @Schema(description = "샘플이름")
    private String sampleName;

}
