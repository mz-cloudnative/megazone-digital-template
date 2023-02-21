package com.megazone.springbootbackend.moim.service;

import com.megazone.springbootbackend.moim.event.KafkaMessageEvent;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {

  public void kafkaMessageInfo(KafkaMessageEvent kafkaMessageEvent) {
    System.out.println(String.format("[kafkaMessage] ==> topic:%s, message:%s, offsets:%s", kafkaMessageEvent.getTopic(), kafkaMessageEvent.getMessage(), kafkaMessageEvent.getOffsets().toString()));
  }
}
