package com.megazone.springbootbackend.community.kafka.hit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewCountProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.view-count}")
    private String topic;

    public void sendViewCount(String articleId) {
        kafkaTemplate.send(topic, articleId);
    }
}