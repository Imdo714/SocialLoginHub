package com.api.domain.JoinTest.V2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;

    @Column(name = "school_name")
    private String name;

    public School(){}

    public School(String name) {
        this.name = name;
    }
}
