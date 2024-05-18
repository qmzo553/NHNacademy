package com.example.demo.aop;

import com.example.demo.command.MyCommands;
import com.example.demo.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountAopTest {

    @Mock
    private MyCommands myCommands;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AccountAop accountAop;

    private MyCommands proxy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        AspectJProxyFactory factory = new AspectJProxyFactory(myCommands);
        factory.addAspect(accountAop);
        proxy = factory.getProxy();
    }

    @Test
    @DisplayName("login")
    void testBeforeLogin() {
        when(myCommands.login("user1", "password1")).thenReturn("loginSuccess");
        String result = proxy.login("user1", "password1");

        verify(myCommands).login("user1", "password1");
        assertEquals("loginSuccess", result);
    }

    @Test
    @DisplayName("logout")
    void testBeforeLogout() {
        when(myCommands.logout()).thenReturn("logoutSuccess");
        String result = proxy.logout();

        verify(myCommands).logout();
        assertEquals("logoutSuccess", result);
    }
}
