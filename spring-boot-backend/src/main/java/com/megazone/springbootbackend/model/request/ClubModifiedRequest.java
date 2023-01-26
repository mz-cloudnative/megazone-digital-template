package com.megazone.springbootbackend.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubModifiedRequest {
    private String id;
    private String name;
    private String abbr;
    private String website;
    private String stadium;
    private boolean status;
}
