package com.megazone.springbootbackend.moim.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class KafkaMessageEvent extends ApplicationEvent {

  private String topic;
  private String message;
  private Long offsets;

  public KafkaMessageEvent(Object source, String topic, String message, Long offsets) {
    super(source);
    this.topic = topic;
    this.message = message;
    this.offsets = offsets;
  }
}
