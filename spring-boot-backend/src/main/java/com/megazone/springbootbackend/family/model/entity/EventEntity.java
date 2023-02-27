package com.megazone.springbootbackend.family.model.entity;

import com.megazone.springbootbackend.family.model.domain.Event;
import com.megazone.springbootbackend.family.model.domain.Family;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "event")
public class EventEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_id", nullable = false)
  private Long id;

  private String name;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;

  private Alarm alarm;
  private LocalDateTime alarmDateTime;

  //@JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "family_id", nullable = false)
  private FamilyEntity family;

  public static EventEntity fromDomainEntity(Event event) {
    return EventEntity.builder()
        .id(event.getId())
        .name(event.getName())
        .startDateTime(event.getStartDateTime())
        .endDateTime(event.getEndDateTime())
        .alarm(event.getAlarm())
        .alarmDateTime(event.getAlarmDateTime())
        .family(FamilyEntity.fromDomainEntity(event.getFamily()))
        .build();
  }

  public Event toDomainEntity() {
    return Event.builder()
        .id(this.id)
        .name(this.name)
        .startDateTime(this.startDateTime)
        .endDateTime(this.endDateTime)
        .alarm(this.alarm)
        .alarmDateTime(this.alarmDateTime)
        .family(Family.builder()
            .id(this.family.getId())
            .name(this.family.getName())
            .memberId(this.family.getMemberId())
            .build())
        .build();
  }
}