package com.megazone.springbootbackend.moim.model.domain;

import java.time.LocalDateTime;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoimStudyRoomDetail {

  private Long roomDetailId;
  private String roomDetailName;
  private LocalDateTime createDtt;
  private LocalDateTime updateDtt;
  private int roomAbleNum;
}
