package com.api.OAuth.Jwt;

import java.sql.Timestamp;
import java.util.Date;

public interface JwtInterface {

    String getAccess(String userName); // Access Token 생성
    Timestamp getAccessExpiration(String AccessToken); // Access Token 만료시간
    String getRefresh(String userName); // Refresh Token 생성
    Timestamp getRefreshExpiration(String RefreshToken); // Refresh Token 만료시간
}
