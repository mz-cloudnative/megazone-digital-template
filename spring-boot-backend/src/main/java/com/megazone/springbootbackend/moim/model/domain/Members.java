package com.megazone.springbootbackend.moim.model.domain;

import java.time.LocalDateTime;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Members {

  private Long memberId;
  private String memberName;
  private LocalDateTime createDtt;
  private LocalDateTime updateDtt;

}
