package com.nhnacademy.customerservice.interceptor;

import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DbConnectionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        DbConnectionThreadLocal.initialize();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        DbConnectionThreadLocal.reset();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
