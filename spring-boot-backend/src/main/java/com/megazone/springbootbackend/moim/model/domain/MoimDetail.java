package com.megazone.springbootbackend.moim.model.domain;

import java.time.LocalDateTime;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoimDetail {

  private Long moimDetailId;
  private String moimDetailContent;
  private String moimDetailableNum;
  private String moimDetailPlace;
  private LocalDateTime createDtt;
  private LocalDateTime updateDtt;

}
