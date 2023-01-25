package com.megazone.springbootbackend.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "players")
public class PlayersEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @NotNull
    @Column(name = "back_number", nullable = false)
    private Integer backNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club", nullable = false)
    private ClubsEntity club;

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
    private Date joined;

    @NotNull
    @Column(name = "birth", nullable = false)
    private Date birth;
}
