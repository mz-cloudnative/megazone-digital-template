package com.megazone.springbootbackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubAddDto {
    private String name;
    private String abbr;
    private String stadium;
    private String website;
}
