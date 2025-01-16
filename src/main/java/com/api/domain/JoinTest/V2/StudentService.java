package com.api.domain.JoinTest.V2;

import com.api.domain.JoinTest.V2.Entity.ClassEntity;
import com.api.domain.JoinTest.V2.Entity.School;
import com.api.domain.JoinTest.V2.Entity.Student;
import com.api.domain.JoinTest.V2.Repository.ClassEntityRepository;
import com.api.domain.JoinTest.V2.Repository.SchoolRepository;
import com.api.domain.JoinTest.V2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private final ClassEntityRepository classEntityRepository;

    @Transactional
    public void createDummyData() {
        // School 생성
        School school = new School("Spring High School");
        schoolRepository.save(school);

        // ClassEntity 생성
        ClassEntity classEntity = new ClassEntity("Class A");
        classEntityRepository.save(classEntity);

        // Member 생성
        Student member = new Student("John Doe", school, classEntity);
        studentRepository.save(member);
    }

    public Student findMember(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found!"));
    }
}
