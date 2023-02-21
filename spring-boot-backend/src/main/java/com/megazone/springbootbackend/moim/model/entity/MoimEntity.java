package com.megazone.springbootbackend.moim.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.megazone.springbootbackend.moim.model.domain.Status;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "moim_info")
@SequenceGenerator(name = "MOIM_SEQ_GEN", sequenceName = "MOIM_SEQ", initialValue = 1, allocationSize = 1)
public class MoimEntity extends BaseTimeEntity {

  @Id
  @Column(name = "moim_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOIM_SEQ_GEN")
  private Long moimId;

  @Column(name = "moim_name", nullable = false)
  private String moimName;

  @Column(name = "moim_administrator", nullable = false)
  private String moimAdministrator;

  @Enumerated(EnumType.STRING)
  @Column(name = "moim_status", nullable = false)
  private Status moimStatus;

  @OneToMany(mappedBy = "moimEntity", cascade = CascadeType.ALL)
  private List<MoimDetailEntity> moimDetailEntity = new ArrayList<>();

  @Builder
  public MoimEntity(Long moimId, String moimName, String moimAdministrator, Status moimStatus, List<MoimDetailEntity> moimDetailEntity) {
    this.moimId = moimId;
    this.moimName = moimName;
    this.moimAdministrator = moimAdministrator;
    this.moimStatus = moimStatus;
    this.moimDetailEntity = moimDetailEntity;
  }
}
