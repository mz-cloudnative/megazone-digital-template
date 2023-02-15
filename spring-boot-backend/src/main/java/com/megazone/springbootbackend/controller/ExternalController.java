package com.megazone.springbootbackend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megazone.springbootbackend.config.WebClientConfig;
import com.megazone.springbootbackend.model.rawJson.ExternalCountry;
import com.megazone.springbootbackend.model.rawJson.RawExternal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExternalController {
    @Value("${spring.rest.connect-time}")
    private int connectTimeout;
    @Value("${spring.rest.read-time}")
    private int readTimeout;
    @Value("${info.external.url}")
    private String url;
    @Value("${info.external.header.key}")
    private String key;
    @Value("${info.external.header.host}")
    private String host;

    private final WebClient webClient;


    @GetMapping("/testRTGet")
    public List<ExternalCountry> getExternalCountry() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectTimeout);

        RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders header = new HttpHeaders();
        header.set("X-RapidAPI-Key", key);
        header.set("X-RapidAPI-Host", host);

        HttpEntity<String> entity = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        Gson gson = new GsonBuilder().create();
        RawExternal external = gson.fromJson(response.getBody(), RawExternal.class);
        return external.getResponse();
    }

    @GetMapping("/testWCGet")
    public void findAll() {

    }

}
