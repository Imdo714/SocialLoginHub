package com.api.domain.JoinTest.Repository;

import com.api.domain.JoinTest.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.bno = :bno")
    List<Object[]> getBoardWithWriterByBno(@Param("bno") Long bno);

//    @Query("SELECT b FROM Board b JOIN b.writer w WHERE w.email = :email")
//    List<Board> findBoardsByWriterEmail(@Param("email") String email);

    @Query("SELECT b, w FROM Board b JOIN FETCH b.writer w WHERE w.email = :email")
    List<Board> findBoardsWithWriterByEmail(@Param("email") String email);
}
