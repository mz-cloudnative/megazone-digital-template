package com.megazone.springbootbackend.moim.service;

import com.megazone.springbootbackend.moim.event.KafkaMessageEvent;
import com.megazone.springbootbackend.moim.event.MoimEvent;
import com.megazone.springbootbackend.moim.event.publisher.GenericEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommonFacade {

  private final ApplicationEventPublisher applicationEventPublisher;
  private final GenericEventPublisher<String> genericEventPublisher;

//  private final MoimService moimService;
//  private final StudyRoomService studyRoomService;

  @Transactional
  public void createMoim(String administrator, String moimName) {
    applicationEventPublisher.publishEvent(new MoimEvent(this, moimName, administrator, ""));
  }

  public void genericType(String message, boolean success) {
    genericEventPublisher.publish(message, success);
  }

  public void processMessage(String topic, String message, Long offset) {
    applicationEventPublisher.publishEvent(new KafkaMessageEvent(this, topic, message, offset));
  }
}
