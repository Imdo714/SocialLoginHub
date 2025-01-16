package com.api.domain.JoinTest.V1.Repository;

import com.api.domain.JoinTest.V1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
