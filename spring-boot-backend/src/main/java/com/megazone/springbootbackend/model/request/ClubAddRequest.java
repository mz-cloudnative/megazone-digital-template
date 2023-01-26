package com.megazone.springbootbackend.model.request;

import lombok.Getter;

@Getter
public class ClubAddRequest {
    String name;
    String abbr;
    String stadium;
    String url;
    String status;
}
