package com.api.domain.JoinTest.V2.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id") // 외래 키 컬럼 이름
    private School school;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "class_id") // 외래 키 컬럼 이름
//    private ClassEntity classEntity;

    @ManyToMany
    @JoinTable(
            name = "student_class", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "student_id"), // 현재 엔티티의 외래 키
            inverseJoinColumns = @JoinColumn(name = "class_id") // 연관된 엔티티의 외래 키
    )
//    @ToString.Exclude // 순환 참조 방지
    private List<ClassEntity> classes = new ArrayList<>();

    public Student(){}

    // 더미데이터 생성자 받기
//    public Student(String name, School school, ClassEntity classEntity) {
//        this.name = name;
//        this.school = school;
//        this.classEntity = classEntity;
//    }

    public Student(String name, School school) {
        this.name = name;
        this.school = school;
    }
}
