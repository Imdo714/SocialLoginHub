package com.api.User.Dto;

import com.api.User.Entity.TokenEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenDTO {

    private int userid;
    private String accessToken;
    private LocalDateTime accessExpirationTime;
    private String refreshToken;
    private LocalDateTime refreshExpirationTime;

    public TokenDTO(){};

    public TokenDTO(TokenEntity token) {
        this.userid = token.getUserid();
        this.accessToken = token.getAccessToken();
        this.accessExpirationTime = token.getAccessExpirationTime().toLocalDateTime();
        this.refreshToken = token.getRefreshToken();
        this.refreshExpirationTime = token.getRefreshExpirationTime().toLocalDateTime();
    }
}
