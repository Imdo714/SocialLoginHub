package com.api.domain.JoinTest.V1.Repository;

import com.api.domain.JoinTest.V1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
