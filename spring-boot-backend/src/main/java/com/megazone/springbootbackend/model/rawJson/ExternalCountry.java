package com.megazone.springbootbackend.model.rawJson;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExternalCountry {
    private String name;
    private String code;
    private String flag;
}
