package com.api.User.Controller;

import com.api.OAuth.Dto.CustomUser;
import com.api.User.Dto.TokenDTO;
import com.api.User.Entity.TokenEntity;
import com.api.User.Entity.UserEntity;
import com.api.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test-login")
    public String testLogin(){
        return "세션정보 확인";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CustomUser customUser){
        log.info("로그인 성공 = {}", customUser);

        TokenDTO res = userService.token(customUser);
        log.info("res = {}", res);

        return "로그인 성공";
    }

    @GetMapping("/loginFailure")
    public String loginFailure(){
        return "로그인 실패";
    }




}
