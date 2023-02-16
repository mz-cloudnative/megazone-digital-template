package com.megazone.springbootbackend.moim.event.listener;

import com.megazone.springbootbackend.moim.event.MoimEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MoimEventListener {

  @Async
  @EventListener
  public void makeInviteTicket(MoimEvent moimEvent) throws InterruptedException{
    Thread.sleep(1000);
    System.out.println(String.format("모임을 위한 초대장을 발급하였습니다. 모임명 : %s", moimEvent.getMoimName()));
  }

  @Async
  @EventListener
  public void inviteSend(MoimEvent moimEvent) throws InterruptedException {
    Thread.sleep(3000);
    System.out.println(String.format("%s님에게 초대장을 보냈습니다. 모임명 : %s", moimEvent.getUserName(), moimEvent.getMoimName()));
  }

  @Async
  @EventListener
  public void inviteComplete(MoimEvent moimEvent) throws InterruptedException {
    Thread.sleep(5000);
    System.out.println(String.format("%s님이 초대가 완료되었습니다. 모임명 : %s", moimEvent.getUserName(), moimEvent.getMoimName()));
  }

}
