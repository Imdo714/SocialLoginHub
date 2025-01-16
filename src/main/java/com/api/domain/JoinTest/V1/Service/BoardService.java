package com.api.domain.JoinTest.V1.Service;

import com.api.domain.JoinTest.V1.Repository.BoardImgRepository;
import com.api.domain.JoinTest.V1.Repository.BoardRepository;
import com.api.domain.JoinTest.V1.entity.Board;
import com.api.domain.JoinTest.V1.entity.BoardImg;
import com.api.domain.JoinTest.V2.Entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardImgRepository boardImgRepository;
    private final BoardRepository boardRepository;

    public void createDummyData() {
        // BoardImg 생성
        BoardImg boardImg1 = new BoardImg("http://example.com/image1.jpg");
        BoardImg boardImg2 = new BoardImg("http://example.com/image2.jpg");
        boardImgRepository.save(boardImg1);
        boardImgRepository.save(boardImg2);

        // Board 생성
        Board board1 = new Board("Board 1", "This is content 1.", boardImg1);
        Board board2 = new Board("Board 2", "This is content 2.", boardImg2);
        boardRepository.save(board1);
        boardRepository.save(board2);
    }

    public Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found!"));
    }
}
