package com.api.domain.JoinTest.V1;

import com.api.domain.JoinTest.V1.Repository.BoardRepository;
import com.api.domain.JoinTest.V1.Repository.MemberRepository;
import com.api.domain.JoinTest.V1.Service.BoardService;
import com.api.domain.JoinTest.V1.dto.BoardWithWriterDTO;
import com.api.domain.JoinTest.V1.entity.Board;
import com.api.domain.JoinTest.V1.entity.Member;
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
