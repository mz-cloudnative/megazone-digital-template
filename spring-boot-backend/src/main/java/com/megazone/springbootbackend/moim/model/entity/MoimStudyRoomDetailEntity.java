package com.megazone.springbootbackend.moim.model.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moim_study_room_detail_info")
@SequenceGenerator(name = "MOIM_STUDY_ROOM_DETAIL_SEQ_GEN", sequenceName = "MOIM_STUDY_ROOM_DETAIL_SEQ", initialValue = 1, allocationSize = 1)
public class MoimStudyRoomDetailEntity extends BaseTimeEntity{

  @Id
  @Column(name = "room_detail_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOIM_STUDY_ROOM_DETAIL_SEQ_GEN")
  private Long roomDetailId;

  @Column(name = "room_detail_content", nullable = false)
  private String roomDetailContent;

  @Column(name = "room_able_num", nullable = false)
  private int roomAbleNum;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", updatable = false)
  private MoimStudyRoomEntity moimStudyRoomEntity;
}
