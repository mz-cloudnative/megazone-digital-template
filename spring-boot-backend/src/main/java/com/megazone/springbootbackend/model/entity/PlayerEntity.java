package com.megazone.springbootbackend.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "players")
public class PlayerEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "back_number")
    private Integer backNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club", nullable = false)
    private ClubEntity club;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "nationality", nullable = false)
    private String nationality;

    @NotNull
    @Column(name = "position", nullable = false)
    private String position;

    @NotNull
    @Column(name = "joined", nullable = false)
    private String joined;

    @NotNull
    @Column(name = "birth", nullable = false)
    private String birth;
}
