package com.megazone.springbootbackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerAddDto {
    private String name;
    private String clubId;
    private String nationality;
    private String joined;
    private String birth;
}
