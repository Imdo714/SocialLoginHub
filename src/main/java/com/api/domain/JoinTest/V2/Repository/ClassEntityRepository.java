package com.api.domain.JoinTest.V2.Repository;

import com.api.domain.JoinTest.V2.Entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Long> {
}
