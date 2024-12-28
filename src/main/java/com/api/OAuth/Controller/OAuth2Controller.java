package com.api.OAuth.Controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
public class OAuth2Controller {

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        model.addAttribute("name", oAuth2User.getAttribute("name")); // 사용자 이름 표시
        model.addAttribute("email", oAuth2User.getAttribute("email")); // 사용자 이메일 표시
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");

//        log.info("name = {}", name);
//        log.info("email = {}", email);

        return "loginSuccess"; // 성공 화면
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure"; // 실패 화면
    }



}
