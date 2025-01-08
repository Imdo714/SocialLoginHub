package com.api.JoinTest.Service;

import com.api.JoinTest.dto.BoardDTO;
import com.api.JoinTest.dto.BoardWithWriterDTO;
import com.api.JoinTest.entity.Board;
import com.api.JoinTest.entity.Member;

import java.util.List;

public interface BoardService {

    void get(Long bno);

    List<BoardWithWriterDTO> boardJoin(String email);

}
