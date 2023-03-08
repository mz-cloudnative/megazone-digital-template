package com.megazone.springbootbackend.family.service.handler;

import com.megazone.springbootbackend.family.model.event.SpecialDayEvent;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : FamilyEventHandler</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/27</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/27 4:59 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Slf4j
@Component
public class FamilyEventHandler {


  private final CountDownLatch latch = new CountDownLatch(1);

  @Async("getAsyncExecutor")
  @Order(value = 1)
  @EventListener(SpecialDayEvent.class)
  public void registerAsyncHandle(SpecialDayEvent specialDay) {
    log.info("{}가 등록되었습니다.1", specialDay.getName());
    latch.countDown();
  }

  @Async("getAsyncExecutor")
  @Order(value = 2)
  @EventListener(SpecialDayEvent.class)
  public void alarmAsyncHandle(SpecialDayEvent specialDay) {
    try {
      latch.await();
    } catch (InterruptedException e) {
      log.error(e.getMessage());
      Thread.currentThread().interrupt();
      return;
    }
    log.info("{}를 확인해보세요.2", specialDay.getName());
  }
}
