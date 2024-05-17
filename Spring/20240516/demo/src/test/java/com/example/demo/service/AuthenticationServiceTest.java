package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.file.DataParser;
import com.example.demo.properties.FileProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private DataParser dataParser;

    @Mock
    private FileProperties fileProperties;

    @BeforeEach
    public void setUp() {
        authenticationService.setAccountList(
                Arrays.asList(
                        new Account("1", "password1", "John"),
                        new Account("2", "password2", "Jane")
                )
        );
        authenticationService.setCurrentLoginList(new ArrayList<>());
    }

    @Test
    @DisplayName("empty current login list")
    void testGetCurrentLoginList_Empty() {
        assertEquals("", authenticationService.getCurrentLoginList());
    }

    @Test
    @DisplayName("success login")
    void testLogin_Success() {
        Account account = authenticationService.login("1", "password1");
        assertNotNull(account);
        assertEquals("1", account.getId());
        assertEquals("John", account.getName());
    }

    @Test
    @DisplayName("failed login")
    void testLogin_Failure() {
        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.login("id", "password");
        });
    }

    @Test
    @DisplayName("is login")
    void testIsLogin() {
        assertFalse(authenticationService.isLogin());
        authenticationService.login("1", "password1");
        assertTrue(authenticationService.isLogin());
    }

    @Test
    @DisplayName("logout")
    void testLogout() {
        authenticationService.login("1", "password1");
        String message = authenticationService.logout();
        assertEquals("John good bye", message);
        assertFalse(authenticationService.isLogin());
    }
}
