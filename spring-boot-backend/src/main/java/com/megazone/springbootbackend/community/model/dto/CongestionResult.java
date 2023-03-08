package com.megazone.springbootbackend.community.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CongestionResult {
    private String congestionTrain;
    private String congestionCar;
    private int congestionType;

    public CongestionResult(String congestionTrain, String congestionCar, int congestionType) {
        this.congestionTrain = congestionTrain;
        this.congestionCar = congestionCar;
        this.congestionType = congestionType;
    }
}
