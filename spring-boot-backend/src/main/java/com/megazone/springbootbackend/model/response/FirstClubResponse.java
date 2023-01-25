package com.megazone.springbootbackend.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FirstClubResponse {
    private String id;
    private String name;
    private String abbr;
    private String stadium;
    private String website;
}
