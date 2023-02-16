package com.megazone.springbootbackend.moim.event.listener;

import com.megazone.springbootbackend.moim.event.GenericEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GenericEventListener {

  @EventListener(condition = "#event.success")
  public void handleEvent(GenericEvent event) {
    System.out.println("Received Spring generic event by annotaion.. ==> " + event.getResult());
  }
}
