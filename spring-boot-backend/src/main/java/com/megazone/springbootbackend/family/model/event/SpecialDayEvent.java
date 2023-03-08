package com.megazone.springbootbackend.family.model.event;

import com.megazone.springbootbackend.family.model.domain.Family;
import com.megazone.springbootbackend.family.model.domain.SpecialDay;
import com.megazone.springbootbackend.family.model.entity.Alarm;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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