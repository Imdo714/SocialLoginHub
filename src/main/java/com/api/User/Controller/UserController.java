package com.api.User.Controller;

import com.api.OAuth.Dto.CustomUser;
import com.api.User.Dto.TokenDTO;
import com.api.User.Entity.TokenEntity;
import com.api.User.Entity.UserEntity;
import com.api.User.Service.UserService;
import com.api.Utils.CookieUtil.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal CustomUser customUser, HttpServletResponse response){
        if (customUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        TokenDTO res = userService.token(customUser);

        Cookie refreshTokenCookie = CookieUtil.createCookie("RefreshToken", res.getRefreshToken(), -1);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

    @GetMapping("/loginFailure")
    public ResponseEntity<?> loginFailure(@RequestParam(required = false) String error){
        log.info("error = {}", error);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 실패");
    }



}
