package com.api.OAuth.CustomHandler;

import com.api.User.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final UserService userService;

    public CustomLogoutSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /**
         *  Authentication는 Spring Security에서 인증된 사용자의 정보를 담고 있는 객체
         */
        if(authentication != null){
            String username = authentication.getName();
            log.info("username = {}", username);
            userService.deleteDataAccess(username);

            response.sendRedirect("/");
        }

    }
}
