package com.api.domain.JoinTest;

import com.api.domain.JoinTest.Repository.BoardRepository;
import com.api.domain.JoinTest.Repository.MemberRepository;
import com.api.domain.JoinTest.Service.BoardService;
import com.api.domain.JoinTest.dto.BoardWithWriterDTO;
import com.api.domain.JoinTest.entity.Board;
import com.api.domain.JoinTest.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/aa")
    public void rea(){
        Member member = Member.builder()
                .email("testuser@example.com")
                .password("password123")
                .name("Test User")
                .build();
        memberRepository.save(member);

        Board board = Board.builder()
                .title("Board")
                .content("This")
                .writer(member) // Member 객체와 연관
                .build();

        Board board2 = Board.builder()
                .title("Sample")
                .content("This is")
                .writer(member) // Member 객체와 연관
                .build();

        boardRepository.save(board);
        boardRepository.save(board2);
    }

    @GetMapping("/{bno}")
    public void re2(@PathVariable long bno){
//        BoardDTO boardDTO = boardService.get(bno);
        boardService.get(bno);
        String email = "testuser@example.com";
        List<BoardWithWriterDTO> list =  boardService.boardJoin(email);
        log.info("최종 = {}", list);
    }

}
