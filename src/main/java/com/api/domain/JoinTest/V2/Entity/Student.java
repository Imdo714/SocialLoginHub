package com.api.domain.JoinTest.V2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id") // 외래 키 컬럼 이름
//    @JsonIgnore // 직렬화에서 제외
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id") // 외래 키 컬럼 이름
//    @JsonIgnore // 직렬화에서 제외
    private ClassEntity classEntity;

    public Student(){}

    public Student(String name, School school, ClassEntity classEntity) {
        this.name = name;
        this.school = school;
        this.classEntity = classEntity;
    }
}
