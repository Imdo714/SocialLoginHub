package com.api.domain.JoinTest.Service;

import com.api.domain.JoinTest.dto.BoardWithWriterDTO;

import java.util.List;

public interface BoardService {

    void get(Long bno);

    List<BoardWithWriterDTO> boardJoin(String email);

}
