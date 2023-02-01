package com.megazone.springbootbackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerModifyDto {
    private String id;
    private Integer backNumber;
    private String name;
    private String club;
    private String nationality;
    private String position;
    private String joined;
    private String birth;
}
