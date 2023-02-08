package com.junwork.chatbot.global.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

public class HttpUtils {
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue){
        setCookie(response, cookieName, cookieValue, DateUtils.Period.DAY.getSeconds(), "/");
    }
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge, String path) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static Optional<Cookie> findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst();
    }
    public static void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    public static void createSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }
    public static void expireSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }
    public interface SessionKey {
        public static final String LOGIN_MEMBER = "loginMember";
    }
}
