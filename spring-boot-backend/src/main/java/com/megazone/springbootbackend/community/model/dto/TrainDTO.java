package com.megazone.springbootbackend.community.model.dto;

import lombok.Data;

@Data
public class TrainDTO {
    private boolean success;
    private int code;
    private String msg;
    private TrainData trainData;

    public TrainDTO(boolean success, int code, String msg, TrainData trainData) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.trainData = trainData;
    }
}
