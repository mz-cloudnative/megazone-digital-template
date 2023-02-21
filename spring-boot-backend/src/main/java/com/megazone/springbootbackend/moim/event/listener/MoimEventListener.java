package com.megazone.springbootbackend.moim.event.listener;

import com.megazone.springbootbackend.moim.event.MoimEvent;
import com.megazone.springbootbackend.moim.service.MoimService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class MoimEventListener {

  private final MoimService moimService;

  @Async
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @TransactionalEventListener
  public void makeInviteTicket(MoimEvent moimEvent){
    moimService.createMoimTicket(moimEvent.getMoimName(), moimEvent.getAdministrator());
    System.out.println(String.format("모임을 위한 초대장을 발급하였습니다. 모임명[%s], 관리자명[%s]", moimEvent.getAdministrator(), moimEvent.getMoimName()));
  }

//  @Async
  @EventListener
  public void inviteSend(MoimEvent moimEvent){
    moimService.sendToEmail(moimEvent.getMoimName(), moimEvent.getAdministrator());
    System.out.println(String.format("Test 님에게 초대장을 보냈습니다. 모임명[%s]", moimEvent.getMoimName()));
  }

//  @Async
  @EventListener
  public void inviteComplete(MoimEvent moimEvent) throws InterruptedException {
//    Thread.sleep(5000);
    System.out.println(String.format("Test 님이 초대가 완료되었습니다. 모임명 : %s", moimEvent.getMoimName()));
  }

}
