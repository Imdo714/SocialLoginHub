package com.api.domain.JoinTest.V2.Dto;

import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.Data;

@Data
public class StudentListDTO {

    private Long id;
    private String name;
    private String schoolName;

    public StudentListDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.schoolName = student.getSchool().getName();
    }



}
