package com.megazone.springbootbackend.model.request;

import lombok.Getter;

@Getter
public class PlayerModifiedRequest {
    private String id;
    private Integer backNumber;
    private String name;
    private String club;
    private String nationality;
    private String position;
    private String joined;
    private String birth;
}
