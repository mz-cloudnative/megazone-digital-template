package com.megazone.springbootbackend.moim.model.entity;

import com.megazone.springbootbackend.moim.model.domain.Status;
import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "moim_info")
public class MoimEntity extends BaseTimeEntity {

  @Id
  @Column(name = "moim_id", nullable = false)
  private Long moimId;

  @Column(name = "moim_name", nullable = false)
  private String moimName;

  @Enumerated(EnumType.STRING)
  @Column(name = "moim_status", nullable = false)
  private Status moimStatus;

}
