package com.megazone.springbootbackend.family.model.entity;

import com.megazone.springbootbackend.family.model.domain.Family;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "family")
public class FamilyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "family_id", nullable = false)
  private Long id;

  private String name;

  @Column(nullable = false)
  private Long memberId;

  //@JsonManagedReference
  @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
  private List<EventEntity> event;

  @Builder
  public FamilyEntity(Long id, String name, Long memberId, List<EventEntity> event) {
    this.id = id;
    this.name = name;
    this.memberId = memberId;
    this.event = event;
  }

  public static FamilyEntity fromDomainEntity(Family family) {
    return FamilyEntity.builder()
        .id(family.getId())
        .name(family.getName())
        .memberId(family.getMemberId())
        .build();
  }

  public Family toDomainEntity() {
    return Family.builder()
        .id(this.id)
        .name(this.name)
        .memberId(this.memberId)
        .event(
            this.event != null ? this.event.stream()
                .map(EventEntity::toDomainEntity)
                .collect(Collectors.toList()) : Collections.emptyList()
        )
        .build();
  }
}