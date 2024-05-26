package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.service.user.UserService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AccountControllerTest {

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    private User testUser;
    private User testManager;

    @BeforeEach
    void setUp() {
        testUser = User.createUser("user", "123", "user", 10, "01012341234", "a@a");
        testManager = User.createManger("manager", "123", "manager", 10, "01012341234", "a@a");
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountController(userService)).build();
    }

    @Test
    @DisplayName("로그인 화면 출력")
    void login() throws Exception{
        mockMvc.perform(get("/cs/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }

    @Test
    @DisplayName("로그인 redirect")
    void loginRedirect() throws Exception {
        when(userService.getUser(anyString())).thenReturn(testUser);
        mockMvc.perform(get("/cs/login")
                .cookie(new Cookie("SESSION", "user")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/user"));

        when(userService.getUser(anyString())).thenReturn(testManager);
        mockMvc.perform(get("/cs/login")
                        .cookie(new Cookie("SESSION", "manager")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin/"));
    }

    @Test
    @DisplayName("로그인 요청 처리")
    void doLogin() throws Exception {
        when(userService.doLogin(anyString(), anyString())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(testUser);
        mockMvc.perform(post("/cs/login")
                .param("id", "user")
                .param("pwd", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/user"));

        when(userService.doLogin(anyString(), anyString())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(testManager);
        mockMvc.perform(post("/cs/login")
                        .param("id", "manager")
                        .param("pwd", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin/"));

        when(userService.doLogin(anyString(), anyString())).thenReturn(false);
        mockMvc.perform(post("/cs/login")
                        .param("id", "user")
                        .param("pwd", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/login"));
    }

    @Test
    void doLogout() throws Exception {
        mockMvc.perform(get("/cs/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/login"));
    }
}