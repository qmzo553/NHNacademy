package com.example.demo.aop;

import com.example.demo.command.MyCommands;
import com.example.demo.domain.Account;
import com.example.demo.exception.NonLoginedException;
import com.example.demo.service.AuthenticationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceAopTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private PriceAop priceAop;

    @Mock
    private ProceedingJoinPoint pjp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        priceAop = new PriceAop(authenticationService);
    }

    @Test
    @DisplayName("without login")
    void testMethodWithoutLogin() throws Throwable {
        when(authenticationService.isLogin()).thenReturn(false);

        NonLoginedException exception = assertThrows(NonLoginedException.class, () -> {
            priceAop.allMethod(pjp);
        });

        assertEquals("로그인이 필요합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("with login")
    void testMethodWithLogin() throws Throwable {
        Account mockAccount = new Account("1", "password", "John Doe");
        when(authenticationService.isLogin()).thenReturn(true);
        when(authenticationService.getCurrentLogin()).thenReturn(mockAccount);
        when(pjp.proceed()).thenReturn("result");

        Object result = priceAop.allMethod(pjp);

        assertEquals("result", result);
        verify(authenticationService, times(1)).isLogin();
        verify(authenticationService, times(1)).getCurrentLogin();
        verify(pjp, times(1)).proceed();
    }
}
