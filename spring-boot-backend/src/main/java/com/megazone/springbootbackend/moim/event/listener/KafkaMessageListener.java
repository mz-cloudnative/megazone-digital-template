package com.megazone.springbootbackend.moim.event.listener;

import com.megazone.springbootbackend.moim.event.KafkaMessageEvent;
import com.megazone.springbootbackend.moim.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

  private final KafkaMessageService kafkaMessageService;

  @EventListener
  public void kafkaMessage(KafkaMessageEvent kafkaMessageEvent){
    kafkaMessageService.kafkaMessageInfo(kafkaMessageEvent);
  }
}
