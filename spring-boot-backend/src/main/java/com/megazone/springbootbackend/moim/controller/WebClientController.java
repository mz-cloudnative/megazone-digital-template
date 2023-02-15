package com.megazone.springbootbackend.moim.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WebClientController {

  @GetMapping("/webclient/load/{query}")
  public Mono<String> doWebClientLoad(@PathVariable("query") String query) {
    WebClient client = WebClient.create();

    return client.get()
        .uri("https://dapi.kakao.com/v3/search/book?query="+query)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .header(HttpHeaders.AUTHORIZATION, "KakaoAK 66da24650ea95e6d6699a37db3c47219")
        .retrieve().bodyToMono(String.class);
  }

  @GetMapping("/rest/load/{query}")
  public String doRestLoad(@PathVariable("query") String query) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.set(HttpHeaders.AUTHORIZATION, "KakaoAK 66da24650ea95e6d6699a37db3c47219");

    HttpEntity request = new HttpEntity(httpHeaders);

    ResponseEntity<String> responseEntity = null;
    try {
      responseEntity = restTemplate
          .exchange("https://dapi.kakao.com/v3/search/book?query="+query,HttpMethod.GET, request, String.class);
    } catch (RestClientException e) {
      e.printStackTrace();
    }

    return responseEntity.toString();
  }
}
