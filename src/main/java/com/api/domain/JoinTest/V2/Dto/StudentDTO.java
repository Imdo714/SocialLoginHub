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

    // 클라이언트 에게 보내줄 DTO
    // getClassEntity 가 단일일 때
//    public StudentDTO(Student student) {
//        this.id = student.getId();
//        this.name = student.getName();
//        this.schoolName = student.getSchool().getName();
//        this.className = student.getClassEntity().getName();
//        this.schoolId = student.getSchool().getId();
//    }

}
