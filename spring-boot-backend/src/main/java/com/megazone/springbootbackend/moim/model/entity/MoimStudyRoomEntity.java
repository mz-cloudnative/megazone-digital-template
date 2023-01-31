package com.megazone.springbootbackend.moim.model.entity;

import com.megazone.springbootbackend.moim.model.domain.Status;
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
@Table(name = "moim_study_room_info")
public class MoimStudyRoomEntity {

  @Id
  @Column(name = "room_id", nullable = false)
  private Long roomId;

  @Column(name = "room_name", nullable = false)
  private String roomName;

  @Column(name = "create_datetime", nullable = false)
  private LocalDateTime createDtt;

  @Column(name = "create_datetime", nullable = false)
  private LocalDateTime updateDtt;

  @Column(name = "room_status", nullable = false)
  private Status roomStatus;

}
