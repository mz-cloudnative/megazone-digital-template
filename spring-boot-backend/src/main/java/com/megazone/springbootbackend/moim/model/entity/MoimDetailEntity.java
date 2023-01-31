package com.megazone.springbootbackend.moim.model.entity;

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
@Table(name = "moim_detail_info")
public class MoimDetailEntity extends BaseTimeEntity{

  @Id
  @Column(name = "moim_detail_id", nullable = false)
  private Long moimDetailId;

  @Column(name = "moim_detail_content", nullable = false)
  private String moimDetailContent;

  @Column(name = "moim_detail_able_num", nullable = false)
  private String moimDetailableNum;

  @Column(name = "moim_detail_place", nullable = false)
  private String moimDetailPlace;

//  @Column(name = "create_datetime", nullable = false)
//  private LocalDateTime createDtt;
//
//  @Column(name = "update_datetime", nullable = false)
//  private LocalDateTime updateDtt;

}
