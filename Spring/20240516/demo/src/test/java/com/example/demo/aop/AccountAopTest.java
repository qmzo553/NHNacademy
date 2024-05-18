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

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    @DisplayName("login aop")
    void login() {

        MyCommands testCommands = AopTestUtils.getTargetObject(commands);

        testCommands.login("1", "1");

        commands.login("1", "1");

        assertFalse(AopUtils.isAopProxy(testCommands));
        assertTrue(AopUtils.isAopProxy(commands));
    }
}