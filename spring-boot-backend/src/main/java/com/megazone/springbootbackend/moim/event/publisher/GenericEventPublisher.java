package com.megazone.springbootbackend.moim.event.publisher;

import com.megazone.springbootbackend.moim.event.GenericEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GenericEventPublisher <T> {

  private final ApplicationEventPublisher applicationEventPublisher;

  public void publish(final T message, final boolean success) {

    System.out.println("Publishing generic event =====" );

    GenericEvent<T> genericEvent = new GenericEvent<T>(message, success);
    applicationEventPublisher.publishEvent(genericEvent);
  }
}
