package com.megazone.springbootbackend.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StaffResponse {
    private String id;
    private String name;
    private String clubId;
    private String clubName;
    private String nationality;
    private String role;
    private String joined;
    private String birth;
}
