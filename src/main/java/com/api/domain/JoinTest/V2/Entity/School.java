package com.api.domain.JoinTest.V2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public School(){}

    public School(String name) {
        this.name = name;
    }
}
