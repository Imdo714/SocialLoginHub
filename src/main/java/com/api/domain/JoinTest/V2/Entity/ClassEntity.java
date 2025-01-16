package com.api.domain.JoinTest.V2.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//@Data
@Data
@Setter
@Entity
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @Column(name = "class_name")
    private String name;

    @ManyToMany(mappedBy = "classes")
//    @ToString.Exclude // 순환 참조 방지
    private List<Student> students = new ArrayList<>();

    public ClassEntity(){}

    public ClassEntity(String name) {
        this.name = name;
    }
}
