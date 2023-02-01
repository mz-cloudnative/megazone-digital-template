package com.megazone.springbootbackend.model.request;

import lombok.Getter;

@Getter
public class PlayerAddRequest {
    private String name;
    private String clubId;
    private String nationality;
    private String joined;
    private String birth;
}
