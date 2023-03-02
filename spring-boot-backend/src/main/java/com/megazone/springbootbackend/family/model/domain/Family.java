package com.megazone.springbootbackend.family.model.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Family {

  private Long id;
  private String name;
  private Long memberId;
  private List<SpecialDay> specialDay;

}