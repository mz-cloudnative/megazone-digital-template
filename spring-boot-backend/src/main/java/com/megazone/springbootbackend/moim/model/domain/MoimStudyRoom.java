package com.megazone.springbootbackend.moim.model.domain;

import java.time.LocalDateTime;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoimStudyRoom {

  private Long roomId;
  private String roomName;
  private LocalDateTime createDtt;
  private LocalDateTime updateDtt;
  private Status roomStatus;

}
