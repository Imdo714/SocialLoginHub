package com.api.domain.JoinTest.V1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BoardImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_img_id")
    private Long id;

    @Column(name = "img_url")
    private String imgUrl;

    @OneToOne(mappedBy = "boardImg")  // Board와의 양방향 매핑 (Board의 boardImg와 매핑)
    private Board board;  // Board와 연결되는 BoardImg

    // 기본 생성자, Getter, Setter
    public BoardImg() {}

    public BoardImg(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
