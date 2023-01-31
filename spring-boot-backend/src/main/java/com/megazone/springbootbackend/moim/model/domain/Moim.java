package com.megazone.springbootbackend.moim.model.domain;

import java.time.LocalDateTime;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Moim {

  private Long moimId;
  private String moimName;
  private LocalDateTime createDtt;
  private LocalDateTime updateDtt;
  private Status moimStatus;

}
