package com.api.User.Controller;

import com.api.OAuth.Dto.CustomUser;
import com.api.User.Dto.TokenDTO;
import com.api.User.Entity.TokenEntity;
import com.api.User.Entity.UserEntity;
import com.api.User.Redis.TokenRedis;
import com.api.User.Service.UserService;
import com.api.Utils.CookieUtil.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test-login")
    public String testLogin(){
        return "세션정보 확인";
    }

//    @GetMapping("/loginSuccess")
//    public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal CustomUser customUser, HttpServletResponse response){
//        if (customUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//
//        TokenDTO res = userService.token(customUser);
//
//        Cookie refreshTokenCookie = CookieUtil.createCookie("RefreshToken", res.getRefreshToken(), -1);
//        response.addCookie(refreshTokenCookie);
//
//        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
//    }

    @GetMapping("/loginSuccess")
    public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal CustomUser customUser, HttpServletResponse response){
        if (customUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try{
            TokenRedis res = userService.token(customUser);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (RedisConnectionFailureException e){
            throw new RedisConnectionFailureException("RedisConnectionFailure");
        }
    }

    @GetMapping("/loginFailure")
    public ResponseEntity<?> loginFailure(@RequestParam(required = false) String error){
        log.info("error = {}", error);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 실패");
    }

    @RequestMapping("favicon.ico")
    public void returnNoFavicon() {
        // 빈 응답을 반환하여 불필요한 에러를 방지
    }

}
