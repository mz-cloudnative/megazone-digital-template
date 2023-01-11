package com.megazone.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestUser {

    public TestUser(String name) {
        this.name = name;
    }

    @Id
    private Long userId;
    private String name;


}
