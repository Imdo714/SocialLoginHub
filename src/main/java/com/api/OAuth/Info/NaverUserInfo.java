package com.api.OAuth.Info;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class NaverUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; // getAttributes()

    public NaverUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProviderld() {
        log.info("id = {}", attributes.get("id"));
        return (String) attributes.get("id");
    }

    @Override
    public String getProvoder() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getNickname() {
        return (String) attributes.get("name");
    }
}
