package com.megazone.springbootbackend.moim.model.entity;

import com.megazone.springbootbackend.moim.model.domain.Status;
import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moim_study_room_info")
public class MoimStudyRoomEntity extends BaseTimeEntity{

  @Id
  @Column(name = "room_id", nullable = false)
  private Long roomId;

  @Column(name = "room_name", nullable = false)
  private String roomName;

  @Enumerated(EnumType.STRING)
  @Column(name = "room_status", nullable = false)
  private Status roomStatus;

//  @Column(name = "create_datetime", nullable = false)
//  private LocalDateTime createDtt;
//
//  @Column(name = "update_datetime", nullable = false)
//  private LocalDateTime updateDtt;

}
