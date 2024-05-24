package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.repository.user.UserRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountController(userRepository)).build();
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
        mockMvc.perform(get("/cs/login")
                .cookie(new Cookie("SESSION", "customeruser")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/user"));

        mockMvc.perform(get("/cs/login")
                        .cookie(new Cookie("SESSION", "managermanager")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin/"));
    }

    @Test
    void doLogout() {
    }
}