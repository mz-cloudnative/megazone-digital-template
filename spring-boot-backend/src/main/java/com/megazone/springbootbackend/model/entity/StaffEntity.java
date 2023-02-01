package com.megazone.springbootbackend.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "staffs")
public class StaffEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club", nullable = false)
    private ClubEntity club;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "joined", nullable = false)
    private String joined;

    @Column(name = "birth", nullable = false)
    private String birth;
}
