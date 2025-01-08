package com.api.User.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Token")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int tokenId;  // PK로 사용될 고유 식별자

    @Column(name = "user_id")
    private int userid; // FK

    /**
     * CascadeType.PERSIST :부모 엔터티 FK가 저장될때 연관된 자식 엔터티도 저장
     * CascadeType.MERGE : 부모 엔터티 FK가 병합될 때 연관된 자식 엔터티도 병합
     * CascadeType.REMOVE : 부모 엔터티 FK가 삭제될 때 연관된 자식 엔터티도 삭제
     * CascadeType.REFRESH : 부모 엔터티 새로 고쳐질때 (DB 재로딩) 연관된 자식 엔터티도 새로 고침
     * CascadeType.ALL : 위의 모든 Cascade 작업 포함
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",  insertable = false, updatable = false) // UserEntity의 userId를 참조
    private UserEntity user;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "access_expirationTime")
    @CreationTimestamp
    private Timestamp accessExpirationTime;

    @Column(name = "refresh_expirationTime")
    @CreationTimestamp
    private Timestamp refreshExpirationTime;

    public TokenEntity(){};

    @Builder
    public TokenEntity(int tokenId, int userid, UserEntity user, String accessToken, String refreshToken, Timestamp accessExpirationTime, Timestamp refreshExpirationTime) {
        this.tokenId = tokenId;
        this.userid = userid;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }
}
