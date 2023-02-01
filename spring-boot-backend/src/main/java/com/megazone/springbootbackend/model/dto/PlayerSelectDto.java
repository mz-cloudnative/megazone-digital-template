package com.megazone.springbootbackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayerSelectDto {
    private String id;
    private Integer backNumber;
    private String name;
    private String clubId;
    private String clubName;
    private String nationality;
    private String position;
    private String joined;
    private String birth;
}
