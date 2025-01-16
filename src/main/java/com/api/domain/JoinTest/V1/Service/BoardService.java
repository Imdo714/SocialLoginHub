package com.api.domain.JoinTest.V1.Service;

import com.api.domain.JoinTest.V1.dto.BoardWithWriterDTO;

import java.util.List;

public interface BoardService {

    void get(Long bno);

    List<BoardWithWriterDTO> boardJoin(String email);

}
