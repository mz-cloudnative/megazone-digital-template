package com.megazone.springbootbackend.moim.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MoimEvent extends ApplicationEvent {

  private String userName;
  private String moimName;

  public MoimEvent(Object source, String userName, String moimName) {
    super(source);
    this.userName = userName;
    this.moimName = moimName;
  }
}
