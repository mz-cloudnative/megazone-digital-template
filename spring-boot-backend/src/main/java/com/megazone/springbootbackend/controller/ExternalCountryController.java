package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.model.rawJson.ExternalCountry;
import com.megazone.springbootbackend.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExternalCountryController {
    private final ExternalService externalService;


    @GetMapping("/rtGetCountry")
    public List<ExternalCountry> getExternalCountry() {
        return externalService.getExternalCountryToJson();
    }

    @GetMapping("/wcGetCountry")
    public List<ExternalCountry> findAll() {
        return externalService.monoToJson();
    }
}
