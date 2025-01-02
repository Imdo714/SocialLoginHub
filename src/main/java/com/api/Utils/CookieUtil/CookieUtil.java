package com.api.Utils.CookieUtil;

import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtil {

    /**
     * Utils(Utility Class)라는 이름은 일반적으로 자주 사용되는 공통 기능이나 유틸리티 메서드를 모아둔 클래스를 지칭합니다.
     * 이를 통해 코드의 재사용성과 유지보수성을 높이고, 프로젝트의 구조를 깔끔하게 정리할 수 있습니다.
     *
     * 특징 : Static 메서드로 제공되는 경우가 많음 (인스턴스 생성 없이 사용 가능).
     */
    public static Cookie createCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true); // 클라이언트에서 접근 불가 JS로 해킹하는데 그걸 막아줌
        cookie.setSecure(true); // HTTPS에서만 전송
        cookie.setPath("/"); // 애플리케이션 전체에서 접근 가능
        cookie.setMaxAge(maxAge); // 쿠키 만료 시간
//        cookie.setDomain(); // 도메인 설정
        cookie.setAttribute("SameSite", "None"); // SameSite 설정
        return cookie;
    }

}
