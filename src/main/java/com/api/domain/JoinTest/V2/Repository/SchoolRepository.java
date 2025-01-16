package com.api.domain.JoinTest.V2.Repository;

import com.api.domain.JoinTest.V2.Entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
