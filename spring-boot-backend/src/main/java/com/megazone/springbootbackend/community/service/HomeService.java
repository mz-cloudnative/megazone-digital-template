package com.megazone.springbootbackend.community.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.megazone.springbootbackend.community.model.dto.TrainDTO;
import io.swagger.v3.core.util.Json;
import org.springframework.data.keyvalue.core.event.KeyValueEvent;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class HomeService {

    private WebClient webClient;
    public HomeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


    public String getRealTimeSubway() throws JsonProcessingException {
//        Mono<JsonNode> jsonNode=null;
//        webClient.get().uri("/역삼")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve().bodyToMono(String.class).map(s -> {
//                    ObjectMapper mapper = new ObjectMapper ();
//                    try {
//                        JsonNode jsonNode = mapper.readTree(s);
//                        return jsonNode;
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                });
//        return jsonNode;
        String result = webClient.get().uri("http://swopenAPI.seoul.go.kr/api/subway/6e6b454d7a6c65653731514e76474c/json/realtimeStationArrival/0/5/역삼")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class)
                .block();

        return result;
    }

    public String getCongestionTrain(String trainY){
//        String result= webClient.get()
//                //.uri("https://apis.openapi.sk.com/puzzle/congestion-train/rltm/trains/{subwayLine}/{trainY}")
////                .uri(uriBuilder -> uriBuilder.path("https://apis.openapi.sk.com/puzzle/congestion-train/rltm/trains/{subwayLine}/{trainY}")
////                        .build(subwayLine,trainY))
////                .uri(uriBuilder -> uriBuilder
////                        .path("https://apis.openapi.sk.com/puzzle/congestion-train/rltm/trains/2/{trainY}")
////                        .build(trainY)
////                    )
//                .uri("https://apis.openapi.sk.com/puzzle/congestion-train/rltm/trains/2/"+trainY)
//                //.uri(uriBuilder -> uriBuilder.path("https://apis.openapi.sk.com/puzzle/congestion-train/rltm/trains/2/"+trainY)
//                //        .queryParams(queryParams)
//                //        .build())
//                //curl --request GET \
//                //     --url https://apis.openapi.sk.com/puzzle/congestion-train/rltm/trains/2/2303 \
//                //     --header 'accept: application/json' \
//                //     --header 'appkey: 8qF71PnoCK2KsI1X7LybH8ZMJWRnYP7n1a0mGyLW'
//
//
//                .header(HttpHeaders.ACCEPT,  MediaType.APPLICATION_JSON_VALUE)
//                .header("appkey","8qF71PnoCK2KsI1X7LybH8ZMJWRnYP7n1a0mGyLW")
//                .retrieve()
//                .bodyToMono(String.class);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("appkey", "8qF71PnoCK2KsI1X7LybH8ZMJWRnYP7n1a0mGyLW");

        HttpEntity request = new HttpEntity(httpHeaders);

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange("https://apis.openapi.sk.com/puzzle/congestion-train/rltm/trains/2/"+trainY, HttpMethod.GET, request, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        return responseEntity.toString();
    }


}
