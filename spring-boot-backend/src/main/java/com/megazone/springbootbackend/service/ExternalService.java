package com.megazone.springbootbackend.service;

import com.megazone.springbootbackend.model.rawJson.ExternalCountry;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExternalService {
    List<ExternalCountry> getExternalCountryToJson();

    List<ExternalCountry> monoToJson();
}
