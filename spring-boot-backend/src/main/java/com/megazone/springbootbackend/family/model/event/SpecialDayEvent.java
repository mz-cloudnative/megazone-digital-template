package com.megazone.springbootbackend.family.model.event;

import com.megazone.springbootbackend.family.model.domain.Family;
import com.megazone.springbootbackend.family.model.domain.SpecialDay;
import com.megazone.springbootbackend.family.model.entity.Alarm;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>설       명 : 특일 이벤트</li>
 * <li>작   성  자 : mz01-ohyunbk</li>
 * <li>작   성  일 : 2023/03/09 2:28 PM</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * </pre>
 ***************************************************/
@Slf4j
@Getter
public class SpecialDayEvent extends BaseEvent {

  private Long id;
  private final String name;
  private final LocalDateTime startDateTime;
  private final LocalDateTime endDateTime;
  private final Alarm alarm;
  private final LocalDateTime alarmDateTime;
  private final Family family;

  public SpecialDayEvent(SpecialDay specialDay) {
    log.info("{}", getTimestamp());

    this.name = specialDay.getName();
    this.startDateTime = specialDay.getStartDateTime();
    this.endDateTime = specialDay.getEndDateTime();
    this.alarm = specialDay.getAlarm();
    this.alarmDateTime = specialDay.getAlarmDateTime();
    this.family = specialDay.getFamily();
  }

}