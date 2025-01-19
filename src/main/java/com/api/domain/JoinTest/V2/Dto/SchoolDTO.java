package com.api.domain.JoinTest.V2.Dto;

import com.api.domain.JoinTest.V2.Entity.School;
import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SchoolDTO {

    private Long id;
    private String schoolName;
    private List<String> studentList;

    public SchoolDTO(School school) {
        this.id = school.getId();
        this.schoolName = school.getName();
        this.studentList = school.getStudentList().stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    /**
     *   stream : student.getCourses()로 반환된 List<Course>를 Stream API로 변환
     *   map은 Stream에서 각 요소를 변환하는 작업을 수행합니다.
     *   ClassEntity의 객체의 getName 메서드를 호춯하여 ClassEntity 객체의 name 속성을 추출
     *   Stream<Student>를 Stream<String>으로 변환합니다.
     *   Collectors.toList()는 결과를 List로 반환하는 Collector입니다.
     */

}
