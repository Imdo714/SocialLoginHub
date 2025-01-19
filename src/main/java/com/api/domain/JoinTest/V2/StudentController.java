package com.api.domain.JoinTest.V2;

import com.api.domain.JoinTest.V2.Dto.SchoolDTO;
import com.api.domain.JoinTest.V2.Dto.StudentListDTO;
import com.api.domain.JoinTest.V2.Entity.School;
import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/dummy")
    public ResponseEntity<String> createDummyData() {
        studentService.createDummyData();
        return ResponseEntity.ok("Dummy data created!");
    }

    // ClassEntity가 리스트가 아닌 단일 일때
//    @GetMapping("/dummy/{id}")
//    public ResponseEntity<StudentDTO> getMember(@PathVariable Long id) {
//        Student student = studentService.findMember(id);
//        return ResponseEntity.ok(new StudentDTO(student));
//    }

    @GetMapping("/dummy2/{id}")
    public ResponseEntity<StudentListDTO> getMember2(@PathVariable Long id) {
        Student student = studentService.findMember(id);
        log.info("student = {}", student);

        return ResponseEntity.ok(new StudentListDTO(student));
    }

    @GetMapping("/dummy3/{id}")
    public ResponseEntity<SchoolDTO> getMember3(@PathVariable Long id) {
        School school = studentService.findSchool(id);
        log.info("school = {}", school);

        return ResponseEntity.ok(new SchoolDTO(school));
    }

}
