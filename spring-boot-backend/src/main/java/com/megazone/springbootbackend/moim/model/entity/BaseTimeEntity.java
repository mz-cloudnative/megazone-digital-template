package com.megazone.springbootbackend.moim.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

  @CreatedDate
  @Column(name = "create_date", updatable = false, nullable = false)
  private LocalDateTime createDtt;

  @LastModifiedDate
  @Column(name = "update_date", nullable = false)
  private LocalDateTime updateDtt;

}
