package com.megazone.springbootbackend.family.service;

import com.megazone.springbootbackend.family.model.domain.SpecialDay;
import com.megazone.springbootbackend.family.model.event.SpecialDayEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : EventService</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/27</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/27 11:02 AM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyService {

  private final ApplicationEventPublisher publisher;

  public void addEvent(SpecialDay specialDay) {
    log.info("이벤트 발행");
    publisher.publishEvent(new SpecialDayEvent(specialDay));
  }
}
