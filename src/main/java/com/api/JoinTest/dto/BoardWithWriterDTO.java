package com.api.JoinTest.dto;

import com.api.JoinTest.entity.Board;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardWithWriterDTO {

    private Long bno;
    private String title;
    private String content;
    private String writerEmail;
    private String password;
    private String writerName;

    public BoardWithWriterDTO(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writerEmail = board.getWriter().getEmail();
        this.password = board.getWriter().getPassword();
        this.writerName = board.getWriter().getName();
    }

}
