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
    @Column(name = "user_id")
    private int userId;

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
    public TokenEntity(String accessToken, String refreshToken, Timestamp accessExpirationTime, Timestamp refreshExpirationTime) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }
}
