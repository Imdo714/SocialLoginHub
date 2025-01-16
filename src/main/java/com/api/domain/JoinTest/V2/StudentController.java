package com.api.domain.JoinTest.V2;

import com.api.domain.JoinTest.V2.Dto.StudentDTO;
import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/dummy")
    public ResponseEntity<String> createDummyData() {
        studentService.createDummyData();
        return ResponseEntity.ok("Dummy data created!");
    }

    @GetMapping("/dummy/{id}")
    public ResponseEntity<StudentDTO> getMember(@PathVariable Long id) {
        Student student = studentService.findMember(id);
        return ResponseEntity.ok(new StudentDTO(student));
    }
}
