package com.api.domain.JoinTest.V2.Repository;

import com.api.domain.JoinTest.V2.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
