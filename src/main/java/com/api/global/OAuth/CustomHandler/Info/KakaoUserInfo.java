package com.api.global.OAuth.CustomHandler.Info;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; // getAttributes()

    public KakaoUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProviderld() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvoder() {
        return "Kakao";
    }

    @Override
    public String getEmail() {
//        return (String) attributes.get("email");
        return null;
    }

    @Override
    public String getNickname() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("properties");
        return (String) kakaoAccount.get("nickname");
    }
}
