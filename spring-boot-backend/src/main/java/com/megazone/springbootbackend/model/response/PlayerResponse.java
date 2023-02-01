package com.megazone.springbootbackend.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerResponse {
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
