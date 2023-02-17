package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.model.rawJson.ExternalCountry;
import com.megazone.springbootbackend.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExternalCountryController {
    @Value("${info.external.url}")
    private String url;

    private final HttpEntity<?> httpEntity;
    private final RestTemplate restTemplate;
    private final ExternalService externalService;
    private final WebClient webClient;


    @GetMapping("/rtGetCountry")
    public List<ExternalCountry> getExternalCountry() {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return externalService.getExternalCountryToJson(response);
    }

    @GetMapping("/wcGetCountry")
    public List<ExternalCountry> findAll() {
        Mono<String> mono = webClient.get().retrieve()
//                .onStatus(HttpStatus::is4xxClientError, response -> {
//                    return null;
//                })
//                .onStatus(HttpStatus::is5xxServerError, response -> {
//                    return null;
//                })
                // subscribe 발행하고 소멸
                // 비동기 처리는 여러 건의 데이터를 처리할 때 필요. 가공을 위함이 아닌. 비동기 처리 확인은 DB에서 확인
                .bodyToMono(String.class); // Flux<String> 타입
        return externalService.monoToJson(mono);
    }
}
