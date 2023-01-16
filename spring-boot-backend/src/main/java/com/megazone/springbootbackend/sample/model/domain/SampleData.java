package com.megazone.springbootbackend.sample.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class SampleData {
    private Long sampleId;

    private String sampleName;

    private LocalDateTime sampleRegDtt;
}
