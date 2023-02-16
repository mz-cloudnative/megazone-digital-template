package com.megazone.springbootbackend.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megazone.springbootbackend.model.rawJson.ExternalCountry;
import com.megazone.springbootbackend.model.rawJson.RawExternal;
import com.megazone.springbootbackend.service.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class ExternalServiceImpl implements ExternalService {
    @Override
    public List<ExternalCountry> getExternalCountryToJson(ResponseEntity<String> response) {
        Gson gson = new GsonBuilder().create();
        RawExternal external = gson.fromJson(response.getBody(), RawExternal.class);
        return external.getResponse();
    }

    @Override
    public List<ExternalCountry> monoToJson(Mono<String> mono) {
        Gson gson = new GsonBuilder().create();
        RawExternal external = gson.fromJson(mono.block(), RawExternal.class);
        return external.getResponse();
    }
}
