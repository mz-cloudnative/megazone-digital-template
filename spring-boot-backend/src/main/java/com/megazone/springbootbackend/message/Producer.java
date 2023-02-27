package com.megazone.springbootbackend.message;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TEST = "test";

    public void sendTestMessage(String payload) {
        kafkaTemplate.send(TEST, payload);
    }
}
