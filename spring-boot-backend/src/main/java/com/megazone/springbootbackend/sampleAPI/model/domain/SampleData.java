package com.megazone.springbootbackend.sampleAPI.model.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SampleData {
    private Long sampleId;

    private String sampleName;

    private LocalDateTime sampleRegDtt;

}
