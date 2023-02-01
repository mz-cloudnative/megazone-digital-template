package com.megazone.springbootbackend.moim.model.entity;

import com.megazone.springbootbackend.moim.model.domain.Status;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "moim_study_room_info")
@SequenceGenerator(name = "MOIM_STUDY_ROOM_SEQ_GEN", sequenceName = "MOIM_STUDY_ROOM_SEQ", initialValue = 1, allocationSize = 1)
public class MoimStudyRoomEntity extends BaseTimeEntity{

  @Id
  @Column(name = "room_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOIM_STUDY_ROOM_SEQ_GEN")
  private Long roomId;

  @Column(name = "room_name", nullable = false)
  private String roomName;

  @Enumerated(EnumType.STRING)
  @Column(name = "room_status", nullable = false)
  private Status roomStatus;

  @OneToMany(mappedBy = "moimStudyRoomEntity", cascade = CascadeType.ALL)
  private List<MoimStudyRoomDetailEntity> moimStudyRoomDetailEntityList = new ArrayList<>();

  @Builder
  public MoimStudyRoomEntity(Long roomId, String roomName, Status roomStatus, List<MoimStudyRoomDetailEntity> moimStudyRoomDetailEntityList) {
    this.roomId = roomId;
    this.roomName = roomName;
    this.roomStatus = roomStatus;
    this.moimStudyRoomDetailEntityList = moimStudyRoomDetailEntityList;
  }
}
