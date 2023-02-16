package com.megazone.springbootbackend.moim.service;

import com.megazone.springbootbackend.moim.event.publisher.GenericEventPublisher;
import com.megazone.springbootbackend.moim.event.publisher.MoimEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoimService {

  private final MoimEventPublisher moimEventPublisher;
  private final GenericEventPublisher<String> genericEventPublisher;

  public void inviteEvent(String userName, String moimName) {
    System.out.println(String.format("초대 이벤트(모임명) -> %s", moimName));
    moimEventPublisher.publish(userName, moimName);
  }

  public void genericType(String message, boolean success) {
    genericEventPublisher.publish(message, success);
  }
}
