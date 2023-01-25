package com.megazone.springbootbackend.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "clubs")
public class ClubsEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "abbr", nullable = false)
    private String abbr;

    @Column(name = "stadium", nullable = false)
    private String stadium;

    @Column(name = "website", nullable = false)
    private String website;
}
