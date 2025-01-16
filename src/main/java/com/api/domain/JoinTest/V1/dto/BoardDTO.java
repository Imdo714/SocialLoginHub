package com.api.domain.JoinTest.V1.dto;

import com.api.domain.JoinTest.V1.entity.Board;
import lombok.Data;

@Data
public class BoardDTO {
    private Long id;
    private String title;
    private String imgUrl;

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.imgUrl = board.getBoardImg().getImgUrl();
    }
}
