package com.megazone.springbootbackend.moim.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moim_study_room_detail_info")
public class MoimStudyRoomDetailEntity {

  @Id
  @Column(name = "room_detail_id", nullable = false)
  private Long roomDetailId;

  @Column(name = "room_detail_name", nullable = false)
  private String roomDetailName;

  @Column(name = "create_datetime", nullable = false)
  private LocalDateTime createDtt;

  @Column(name = "update_datetime", nullable = false)
  private LocalDateTime updateDtt;

  @Column(name = "room_able_num", nullable = false)
  private int roomAbleNum;
}
