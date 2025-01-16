package com.api.domain.JoinTest.V2.Dto;

import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String schoolName;
    private String className;
    private Long schoolId;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.schoolName = student.getSchool().getName();
        this.className = student.getClassEntity().getName();
        this.schoolId = student.getSchool().getId();
    }
}
