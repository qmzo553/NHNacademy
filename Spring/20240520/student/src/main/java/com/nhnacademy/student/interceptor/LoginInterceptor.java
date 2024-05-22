package com.nhnacademy.student.interceptor;

import com.nhnacademy.student.exception.NotLoginException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isLogin = false;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("SESSION")) {
                    if(StringUtils.hasText(cookie.getValue())) {
                        isLogin = true;
                    }
                }
            }
        }

        if(!isLogin) {
            throw new NotLoginException("로그인이 필요합니다.");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
