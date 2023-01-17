package com.megazone.springbootbackend.hotel.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "hotel_desc")
    private String description;

    @Column(name = "hotel_address")
    private String address;

    @Column(name = "hotel_grade")
    private int grade;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "id")
    private List<Room> rooms;

    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime lastModified;
}
