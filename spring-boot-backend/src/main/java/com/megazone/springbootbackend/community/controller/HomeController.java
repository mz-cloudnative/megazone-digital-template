package com.megazone.springbootbackend.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@Slf4j
public class HomeController {

    private final WebClient webClient;

    //http://swopenAPI.seoul.go.kr/api/subway/6e6b454d7a6c65653731514e76474c/json/realtimeStationArrival/0/5/초지
    public HomeController(WebClient.Builder webClient) {
        this.webClient = WebClient.builder().baseUrl("").build();
    }
}
