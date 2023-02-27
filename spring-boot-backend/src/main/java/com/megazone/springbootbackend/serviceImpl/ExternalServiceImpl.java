package com.megazone.springbootbackend.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megazone.springbootbackend.model.rawJson.ExternalCountry;
import com.megazone.springbootbackend.model.rawJson.RawExternal;
import com.megazone.springbootbackend.service.ExternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {
    private final WebClient webClient;
    private final HttpEntity<?> httpEntity;
    private final RestTemplate restTemplate;
    @Value("${info.external.url}")
    private String url;
    @Override
    public List<ExternalCountry> getExternalCountryToJson() {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        Gson gson = new GsonBuilder().create();
        RawExternal external = gson.fromJson(response.getBody(), RawExternal.class);
        return external.getResponse();
    }

    @Override
    public List<ExternalCountry> monoToJson() {
        Mono<String> mono = webClient.get().retrieve()
//                .onStatus(HttpStatus::is4xxClientError, response -> {
//                    return null;
//                })
//                .onStatus(HttpStatus::is5xxServerError, response -> {
//                    return null;
//                })
                // subscribe 발행하고 소멸
                .bodyToMono(String.class); // Flux<String> 타입
        Gson gson = new GsonBuilder().create();
        RawExternal external = gson.fromJson(mono.block(), RawExternal.class);
        return external.getResponse();
    }
}
