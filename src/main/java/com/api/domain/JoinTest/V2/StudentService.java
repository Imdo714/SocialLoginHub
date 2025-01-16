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
        ClassEntity class1 = new ClassEntity("Class A");
        ClassEntity class2 = new ClassEntity("Class B");
        classEntityRepository.save(class1);
        classEntityRepository.save(class2);

        // Student 생성
        Student student = new Student("John Doe", school);
        student.getClasses().add(class1);
        student.getClasses().add(class2);
        studentRepository.save(student);

    }

    public Student findMember(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found!"));
    }
}
