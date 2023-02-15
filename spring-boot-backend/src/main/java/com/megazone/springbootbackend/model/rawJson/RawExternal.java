package com.megazone.springbootbackend.model.rawJson;

import lombok.Getter;

import java.util.List;

@Getter
public class RawExternal {
    private String get;
    private List<String> paprameters;
    private List<String> errors;
    private Integer results;
    private Paging paging;
    private List<ExternalCountry> response;
}
