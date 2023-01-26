package com.megazone.springbootbackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubModifiedDto {
    private String id;
    private String name;
    private String abbr;
    private String stadium;
    private String website;
    private boolean status;
}
