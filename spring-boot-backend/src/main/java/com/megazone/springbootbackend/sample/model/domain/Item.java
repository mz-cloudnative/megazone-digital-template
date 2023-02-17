package com.megazone.springbootbackend.sample.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

  private String dateKind;
  private String dateName;
  private String isHoliday;
  private Long locdate;
  private Long seq;

}
