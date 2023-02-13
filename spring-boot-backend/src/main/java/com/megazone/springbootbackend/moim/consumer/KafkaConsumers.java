package com.megazone.springbootbackend.moim.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumers {

  @KafkaListener(topics = "#{'${spring.kafka.topics.example}'.split(',')}", groupId = "${spring.kafka.consumer.group-id}", autoStartup = "true")
  public void topic(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) Long offsets) {
    try {
      System.out.println(String.format("[topic] topic : %s, [offsets] offsets : %s, [message] message: %s", topic, offsets, message));
    } catch (Exception e) {
      throw new KafkaException("[ERROR] Message: "+ message);
    }
  }

//  @KafkaListener(id = "error-test", topics = "loc-topic-error", errorHandler = "KafkaCustomErrorHandler")
//  public void errorTestListener(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment) {
//    log.info("==== [RECORD] : " + record.toString());
//    log.info("==== [TOPIC] : " + record.topic());
//    throw new KafkaException("ERROR NOWWWWWW!!");
//  }

}
