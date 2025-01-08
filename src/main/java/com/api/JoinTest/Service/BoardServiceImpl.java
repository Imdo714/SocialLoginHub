package com.api.JoinTest.Service;

import com.api.JoinTest.Repository.BoardRepository;
import com.api.JoinTest.dto.BoardDTO;
import com.api.JoinTest.dto.BoardWithWriterDTO;
import com.api.JoinTest.entity.Board;
import com.api.JoinTest.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    @Override
    public void get(Long bno) {
        List<Object[]> results  = boardRepository.getBoardWithWriterByBno(bno);

        if (!results.isEmpty()) {
            Object[] result = results.get(0); // 첫 번째 결과 가져오기
            Board board = (Board) result[0];
            Member writer = (Member) result[1];

            log.info("board = {}", board);
            log.info("writer = {}", writer);
        } else {
            log.info("No data found for bno = {}", bno);
        }
    }

    @Override
    public List<BoardWithWriterDTO> boardJoin(String email) {
        List<Board> boards = boardRepository.findBoardsWithWriterByEmail(email);
        log.info("boards = {}", boards);


        return boards.stream()
                .map(BoardWithWriterDTO::new)
                .collect(Collectors.toList());
        // collect : stream을 처리한 후, BoardWithWriterDTO 객체들을 새로운 리스트로 반환합니다
    }


}
