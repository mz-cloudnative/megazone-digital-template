package com.megazone.springbootbackend.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonElement;
import com.megazone.springbootbackend.community.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@Slf4j
public class HomeController {

    private final HomeService homeService;

    public HomeController(WebClient.Builder webClient, HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home/getRealTimeArrival")
    public String getRealTimeSubway() throws JsonProcessingException {
        String result= homeService.getRealTimeSubway();
        return result;
    }

    @GetMapping("/home/getCongestionLineNumber2/{trainY}")
    public String getCongestion(@PathVariable("trainY") String trainY){
           String result = homeService.getCongestionTrain(trainY);
           return result;
    }

}
