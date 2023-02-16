package com.megazone.springbootbackend.moim.event.publisher;

import com.megazone.springbootbackend.moim.event.MoimEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MoimEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  public void publish(String userName, String moimName) {
    applicationEventPublisher.publishEvent(new MoimEvent(this, userName, moimName));
  }
}
