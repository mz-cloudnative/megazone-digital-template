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
@Table(name = "moim_info")
public class MoimEntity {

  @Id
  @Column(name = "moim_id", nullable = false)
  private Long moimId;

  @Column(name = "moim_name", nullable = false)
  private String moimName;

  @Column(name = "create_datetime", nullable = false)
  private LocalDateTime createDtt;

  @Column(name = "update_datetime", nullable = false)
  private LocalDateTime updateDtt;

  @Column(name = "moim_status", nullable = false)
  private Status moimStatus;

}
