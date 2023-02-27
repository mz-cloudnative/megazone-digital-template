package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.message.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {
    private final Producer producer;

    @PostMapping("/test")
    public String sendTest(@RequestBody String message) {
        producer.sendTestMessage(message);
        return "success";
    }
}
