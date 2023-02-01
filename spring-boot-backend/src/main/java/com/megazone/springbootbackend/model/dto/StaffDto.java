package com.megazone.springbootbackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StaffDto {
    private String id;
    private String name;
    private String clubId;
    private String clubName;
    private String nationality;
    private String role;
    private String joined;
    private String birth;
}
