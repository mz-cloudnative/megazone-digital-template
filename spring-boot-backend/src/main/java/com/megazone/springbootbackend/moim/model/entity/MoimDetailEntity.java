package com.megazone.springbootbackend.moim.model.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moim_detail_info")
@SequenceGenerator(name = "MOIM_DETAIL_SEQ_GEN", sequenceName = "MOIM_DETAIL_SEQ", initialValue = 1, allocationSize = 1)
public class MoimDetailEntity extends BaseTimeEntity{

  @Id
  @Column(name = "moim_detail_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOIM_DETAIL_SEQ_GEN")
  private Long moimDetailId;

  @Column(name = "moim_detail_content", nullable = false)
  private String moimDetailContent;

  @Column(name = "moim_detail_able_num", nullable = false)
  private int moimDetailableNum;

  @Column(name = "moim_detail_place", nullable = false)
  private String moimDetailPlace;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "moim_id", updatable = false)
  private MoimEntity moimEntity;
}
