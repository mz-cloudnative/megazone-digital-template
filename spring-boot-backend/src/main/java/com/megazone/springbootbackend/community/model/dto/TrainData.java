package com.megazone.springbootbackend.community.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrainData {
    private String subwayLine;
    private String trainY;
    private CongestionResult congestionResult;

    public TrainData(String subwayLine, String trainY, CongestionResult congestionResult) {
        this.subwayLine = subwayLine;
        this.trainY = trainY;
        this.congestionResult = congestionResult;
    }
}
