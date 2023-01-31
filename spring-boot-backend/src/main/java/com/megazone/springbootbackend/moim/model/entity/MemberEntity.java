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
@Table(name = "members")
public class MemberEntity {

  @Id
  @Column(name = "member_id", nullable = false)
  private Long memberId;

  @Column(name = "member_name", nullable = false)
  private String memberName;

  @Column(name = "create_datetime", nullable = false)
  private LocalDateTime createDtt;

  @Column(name = "update_datetime", nullable = false)
  private LocalDateTime updateDtt;

}
