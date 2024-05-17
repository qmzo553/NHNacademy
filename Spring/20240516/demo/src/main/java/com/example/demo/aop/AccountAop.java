package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AccountAop {

    @Pointcut("execution(* com.example.demo.command.MyCommands.login(..))")
    public void loginCut() {
    }

    @Pointcut(value = "args(id, password)", argNames = "id,password")
    public void loginArgs(String id, String password) {
    }

    @Pointcut("execution(* com.example.demo.command.MyCommands.logout())")
    public void logoutCut() {
    }

    @Before(value = "loginCut() && loginArgs(id, password)", argNames = "id,password")
    public void beforeLogin(String id, String password) {
        log.info("login([{}, {}])", id, password);
    }

    @Before("logoutCut()")
    public void beforeLogout() {
        log.info("logout[()]");
    }
}
