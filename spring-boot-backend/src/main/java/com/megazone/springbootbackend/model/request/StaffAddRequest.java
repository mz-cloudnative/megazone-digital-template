package com.megazone.springbootbackend.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StaffAddRequest {
    private String name;
    private String clubId;
    private String nationality;
    private String role;
    private String joined;
    private String birth;
}
