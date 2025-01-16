package com.api.domain.JoinTest.V1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title")
    private String title;

    @Column(name = "board_content")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_img_id")  // BoardImg 외래 키 컬럼
    private BoardImg boardImg;  // Board와 연결되는 BoardImg

    // 기본 생성자, Getter, Setter
    public Board() {}

    public Board(String title, String content, BoardImg boardImg) {
        this.title = title;
        this.content = content;
        this.boardImg = boardImg;
    }


}
