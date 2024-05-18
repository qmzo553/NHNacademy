package com.example.demo.aop;

import com.example.demo.command.MyCommands;
import com.example.demo.service.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AopTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountAopTest {

    @Autowired
    private MyCommands commands;

    @Test
    @DisplayName("login aop")
    void login() {
        commands.login("1", "1");

        assertTrue(AopUtils.isAopProxy(commands));


        MyCommands targetCommands = AopTestUtils.getTargetObject(commands);
        assertFalse(AopUtils.isAopProxy(targetCommands));
    }
}
