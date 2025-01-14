package com.api.domain.JoinTest.Repository;

import com.api.domain.JoinTest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
