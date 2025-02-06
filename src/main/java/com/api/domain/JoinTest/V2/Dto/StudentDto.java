package com.api.domain.JoinTest.V2.Dto;

import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class StudentDto {
    private Long id;
    private String name;
    private String schoolName;

    public StudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.schoolName = student.getSchool().getName();
    }
}
