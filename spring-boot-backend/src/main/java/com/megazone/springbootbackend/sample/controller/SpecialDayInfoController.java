package com.megazone.springbootbackend.sample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.springbootbackend.sample.model.domain.Item;
import com.megazone.springbootbackend.sample.service.SampleService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/special-day")
@RequiredArgsConstructor
@Slf4j
public class SpecialDayInfoController {

  private final SampleService sampleService;
  private final ObjectMapper objectMapper;
  private static WebClient webClient =
      WebClient.builder()
          .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
          .build();

  @GetMapping(value = "/getHoliDeInfoRT", produces = "application/json")
  public List<?> getRsetTemplateRestDelInfo() {
    RestTemplate restTemplate = new RestTemplate();

    MultiValueMap<String, String> valueMap = new LinkedMultiValueMap();
    valueMap.setAll(
        Map.of(
            "solYear", "2023"
            , "solMonth", "01"
            , "ServiceKey",
            "yX5hqo3LJ1iOprsd9HDRR7rfT8xTL5zRwZXzE7aQE34vtPAzGgyzXr8o7KFeLljivyQ9ilchKNZc/fMgsDlVMQ=="
            , "_type", "json"
            , "numOfRows", "20"
        )
    );

    UriComponents uri =
        UriComponentsBuilder
            .fromUriString(
                "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo")
            .queryParams(valueMap)
            .build(false);

    //when
    ResponseEntity<String> response =
        restTemplate.exchange(uri.toUri(),
            HttpMethod.GET,
            null,
            String.class);
    log.info(response.toString());
    return null;
  }

  @GetMapping(value = "/getHoliDeInfoWC", produces = "application/json")
  public List<?> getWebClientRestDelInfo() {
    List<Item> result = new ArrayList<>();

    MultiValueMap<String, String> valueMap = new LinkedMultiValueMap();
    valueMap.setAll(
        Map.of(
            "solYear", "2023"
            , "solMonth", "01"
            , "ServiceKey",
            "yX5hqo3LJ1iOprsd9HDRR7rfT8xTL5zRwZXzE7aQE34vtPAzGgyzXr8o7KFeLljivyQ9ilchKNZc/fMgsDlVMQ=="
            , "_type", "json"
            , "numOfRows", "20"
        )
    );

    Mono<ResponseEntity<String>> response = webClient
        .mutate()
        .build()
        .get()
        .uri(uriBuilder ->
            uriBuilder.path("/getHoliDeInfo")
                .queryParams(valueMap)
                .build()
        )
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
            Mono.error(
                new RuntimeException(
                    String.format("4xx 외부 요청 오류. statusCode: %s, response: %s, header: %s",
                        clientResponse.rawStatusCode(),
                        clientResponse.bodyToMono(String.class),
                        clientResponse.headers().asHttpHeaders()))
            )
        )
        .onStatus(HttpStatus::is5xxServerError, clientResponse ->
            Mono.error(
                new WebClientResponseException(
                    clientResponse.rawStatusCode(),
                    String.format("5xx 외부 시스템 오류. %s", clientResponse.bodyToMono(String.class)),
                    clientResponse.headers().asHttpHeaders(), null, null
                )
            )
        )
        .toEntity(String.class);

    response.subscribe(s -> {
      try {
        Iterator<JsonNode> jsonNodeIterator = objectMapper.readTree(s.getBody())
            .get("response")
            .get("body")
            .get("items")
            .iterator();
        while (jsonNodeIterator.hasNext()) {
          log.info(jsonNodeIterator.next().toString());
        }
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
      log.info("status" + s.getStatusCode());
      log.info("header" + s.getHeaders());
      log.info("body" + s.getBody());
      //log.info(jsonpObject.toString());
    });

    return null;
  }
}
