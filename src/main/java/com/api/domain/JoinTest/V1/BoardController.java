package com.api.domain.JoinTest.V1;

import com.api.domain.JoinTest.V1.Service.BoardService;
import com.api.domain.JoinTest.V1.dto.BoardDTO;
import com.api.domain.JoinTest.V1.entity.Board;
import com.api.domain.JoinTest.V2.Dto.StudentListDTO;
import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/aa")
    public ResponseEntity<String> rea(){
        boardService.createDummyData();
        return ResponseEntity.ok("Dummy data created!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> re2(@PathVariable long id){
        Board board = boardService.findBoard(id);
        log.info("board = {}", board);

        return ResponseEntity.ok(new BoardDTO(board));
    }

}
