package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentLoginController.class)
class StudentLoginControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentLoginController(studentRepository)).build();
    }

    @Test
    @DisplayName("로그인 화면 출력")
    void testLoginGet() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"));
    }

    @Test
    @DisplayName("로그인 redirect 출력")
    void testLoginGetSession() throws Exception {
        mockMvc.perform(get("/login")
                        .cookie(new Cookie("SESSION", "user")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/user"));
    }

    @Test
    @DisplayName("로그인 요청 처리")
    void testLoginPost() throws Exception {
        when(studentRepository.matches(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/login")
                        .param("id", "user")
                        .param("pwd", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/user"))
                .andExpect(cookie().value("SESSION", "user"));
    }

    @Test
    @DisplayName("로그인 실패 처리")
    void testLoginFailed() throws Exception {
        when(studentRepository.matches(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/login")
                .param("id","users")
                .param("pwd","1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(cookie().doesNotExist("SESSION"));
    }

    @Test
    @DisplayName("로그아웃 요청 처리")
    void testLogoutGet() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(cookie().value("SESSION", ""));
    }
}
