package com.example.demo.aop;

import com.example.demo.domain.Account;
import com.example.demo.exception.NonLoginedException;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
@Slf4j
public class PriceAop {

    private final AuthenticationService authenticationService;

    @Pointcut("execution(* com.example.demo.command.MyCommands.*(..)) && !execution(* com.example.demo.command.MyCommands.login(..)) && !execution(* com.example.demo.command.MyCommands.logout(..))")
    public void allMethodsExceptLoginAndLogout() {
    }

    @Around("allMethodsExceptLoginAndLogout()")
    public Object allMethod(ProceedingJoinPoint pjp) throws Throwable {
        if(!authenticationService.isLogin()) {
            throw new NonLoginedException("로그인이 필요합니다.");
        }

        Account account = authenticationService.getCurrentLogin();
        Object result = pjp.proceed();
        log.info("<-----{} {} ({}) ----->", account.getName(), pjp.getSignature(), result);

        return result;
    }
}
