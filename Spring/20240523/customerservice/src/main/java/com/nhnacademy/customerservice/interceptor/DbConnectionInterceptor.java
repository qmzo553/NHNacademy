package com.nhnacademy.customerservice.interceptor;

import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class DbConnectionInterceptor implements HandlerInterceptor {

    private final DbConnectionThreadLocal dbConnectionThreadLocal;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        dbConnectionThreadLocal.initialize();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex != null) {
            dbConnectionThreadLocal.setSqlError(true);
            dbConnectionThreadLocal.reset();
        }

        dbConnectionThreadLocal.setSqlError(false);
        dbConnectionThreadLocal.reset();
    }
}
