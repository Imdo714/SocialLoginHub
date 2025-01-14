package com.api.global.OAuth.CustomHandler.Info;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; // getAttributes() 받아줘 

    public GoogleUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProviderld() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvoder() {
        return "google";
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
