package com.megazone.springbootbackend.family.model.domain;

import com.megazone.springbootbackend.family.model.entity.Alarm;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SpecialDay {

  private Long id;
  private String name;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private Alarm alarm;
  private LocalDateTime alarmDateTime;
  private Family family;
}