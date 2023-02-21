package com.megazone.springbootbackend.moim.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MoimEvent extends ApplicationEvent {

  private String moimName;
  private String administrator;
  private String userName;

  public MoimEvent(Object source, String moimName, String administrator, String userName) {
    super(source);
    this.moimName = moimName;
    this.administrator = administrator;
    this.userName = userName;
  }
}
