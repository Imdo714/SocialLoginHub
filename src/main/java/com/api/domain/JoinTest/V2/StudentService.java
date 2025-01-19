package com.api.domain.JoinTest.V2;

import com.api.domain.JoinTest.V2.Entity.School;
import com.api.domain.JoinTest.V2.Entity.Student;
import com.api.domain.JoinTest.V2.Repository.SchoolRepository;
import com.api.domain.JoinTest.V2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    @Transactional
    public void createDummyData() {
        School school = School.builder()
                .name("Greenwood High")
                .build();

        schoolRepository.save(school);

        Student student1 = Student.builder()
                .name("Alice")
                .school(school) // 연관 관계 설정
                .build();

        Student student2 = Student.builder()
                .name("Bob")
                .school(school) // 연관 관계 설정
                .build();

        studentRepository.saveAll(Arrays.asList(student1, student2));
    }

    public Student findMember(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found!"));
    }

    public School findSchool(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found!"));
    }
}
