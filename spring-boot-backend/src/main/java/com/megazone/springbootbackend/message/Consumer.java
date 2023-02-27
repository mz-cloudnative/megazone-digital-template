package com.megazone.springbootbackend.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @KafkaListener(topics = "test", groupId = "group-1")
    public void consumeTest(String message) {
        log.info("Consumed Message: " + message);
    }
}
