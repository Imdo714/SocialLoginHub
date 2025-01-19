package com.api.domain.User.Dto;

import com.api.domain.User.Entity.TokenEntity;
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
        this.userid = token.getUserEntity().getUserid();
        this.accessToken = token.getAccessToken();
        this.accessExpirationTime = token.getAccessExpirationTime().toLocalDateTime();
        this.refreshToken = token.getRefreshToken();
        this.refreshExpirationTime = token.getRefreshExpirationTime().toLocalDateTime();
    }
}
