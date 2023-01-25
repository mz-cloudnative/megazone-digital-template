package com.megazone.springbootbackend.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ranks")
public class RanksEntity {
    @Id
    @Size(max = 255)
    @Column(name = "club", nullable = false)
    private String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club", nullable = false)
    private ClubsEntity clubs;

    @NotNull
    @Column(name = "rank", nullable = false)
    private Integer rank;

    @NotNull
    @Column(name = "win", nullable = false)
    private Integer win;

    @NotNull
    @Column(name = "draw", nullable = false)
    private Integer draw;

    @NotNull
    @Column(name = "ga", nullable = false)
    private Integer ga;

    @NotNull
    @Column(name = "gd", nullable = false)
    private Integer gd;

    @NotNull
    @Column(name = "gf", nullable = false)
    private Integer gf;

    @NotNull
    @Column(name = "lose", nullable = false)
    private Integer lose;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

}