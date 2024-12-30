package com.api.OAuth.Service;

import com.api.OAuth.Dto.CustomUser;
import com.api.OAuth.Info.*;
import com.api.User.Entity.UserEntity;
import com.api.User.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService  {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // google, kakao, naver
        String accessToken = userRequest.getAccessToken().getTokenValue(); // Access Token
        OAuth2User oAuth2User = super.loadUser(userRequest); // 정보

        log.info("registrationId = {}", registrationId);
        log.info("accessToken = {}", accessToken);
        log.info("oAuth2User = {}", oAuth2User);

        OAuth2UserInfo oAuth2UserInfo = null;
        if("google".equals(registrationId)){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if("kakao".equals(registrationId)){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else if("naver".equals(registrationId)){
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        } else {
            throw new OAuth2AuthenticationException("허용되지 않는 인증입니다.");
        }

        String providerld = oAuth2UserInfo.getProviderld(); // sub, id
        String email = oAuth2UserInfo.getEmail() != null ? oAuth2UserInfo.getEmail() : null;
        String nickname = oAuth2UserInfo.getNickname(); // 이름
        String username = registrationId+"-"+providerld; // google-1110110
        String role = "ROLE_USER";

        UserEntity user = userRepository.findByUsername(username);
        if(user == null){
            user = UserEntity.builder()
                    .username(username)
                    .nickname(nickname)
                    .email(email)
                    .providerid(registrationId)
                    .role(role)
                    .build();
            userRepository.save(user);
            user = userRepository.findByUsername(username);
            log.info("회원 가입 성공");
        }

        return new CustomUser(user.getUsername(), user.getNickname(), user.getEmail());
    }

}
