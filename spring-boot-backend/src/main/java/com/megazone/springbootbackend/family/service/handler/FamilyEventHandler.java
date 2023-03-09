package com.megazone.springbootbackend.family.service.handler;

import com.megazone.springbootbackend.family.model.event.SpecialDayEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
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

  // @Async를 사용하여 이벤트를 비동기로 처리할 시 이벤트가 실패하면 이벤트를 유실하게 됨.
  // 이를 방지하기 위해선 이벤트 저장소에 이벤트를 저장하는 방식으로 처리를 해야 이벤트 유실이 발생하지 않음.

  @Async("getAsyncExecutor")
  @EventListener(SpecialDayEvent.class)
  public void registerAsyncHandle(SpecialDayEvent specialDay) {
    log.info("{}가 등록되었습니다. 비동기 1", specialDay.getName());
  }

  @Async("getAsyncExecutor")
  @EventListener(SpecialDayEvent.class)
  public void alarmAsyncHandle(SpecialDayEvent specialDay) {
    log.info("{}를 확인해보세요. 비동기 2", specialDay.getName());
  }

  @EventListener(SpecialDayEvent.class)
  public void registerHandle(SpecialDayEvent specialDay) {
    log.info("{}가 등록되었습니다. 동기 1", specialDay.getName());
  }

  @EventListener(SpecialDayEvent.class)
  public void alarmHandle(SpecialDayEvent specialDay) {
    log.info("{}를 확인해보세요. 동기 2", specialDay.getName());
  }
}
