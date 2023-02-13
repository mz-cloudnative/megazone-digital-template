package com.megazone.springbootbackend.moim.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("kafka")
@RestController
@RequiredArgsConstructor
public class KafkaProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value("${spring.kafka.topics.example}")
  private String topic;

  @GetMapping("producer/topic-list")
  public String selectTopicList() {
    String customTopic = topic;
    return "regTopicList : "+ customTopic ;
  }

  @GetMapping("producer/{topic}/{message}")
  public String sendMessageForError(@PathVariable("topic") String topic, @PathVariable("message") String message) {
    String messageData = ""; // {"id" :1, category:"books".....}
    try {
      messageData = message;
      kafkaTemplate.send(topic, messageData);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return String.format("sendMessageSuccess => [topic] : %s, message:%s ", topic, messageData);
  }
}
