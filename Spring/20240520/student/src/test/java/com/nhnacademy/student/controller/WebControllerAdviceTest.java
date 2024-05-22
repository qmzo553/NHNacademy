package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("ValidationFailedException 처리 테스트")
    void testHandleValidationFailedException() throws Exception {
        Student newStudent = Student.create("test", "1234", "test", "aa", 100, "good");
        mockMvc.perform(post("/student/register")
                .cookie(new Cookie("SESSION", newStudent.getId()))
                .param("id", newStudent.getId())
                .param("password", newStudent.getPassword())
                .param("name", newStudent.getName())
                .param("email", newStudent.getEmail())
                .param("score", String.valueOf(newStudent.getScore()))
                .param("comment", newStudent.getComment()))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attribute("status", HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("NotLoginException 처리 테스트")
    void testHandleNotLoginException() throws Exception {
        mockMvc.perform(get("/student/register"))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attribute("status", HttpStatus.UNAUTHORIZED));
    }

    @Test
    @DisplayName("StudentNotFoundException 처리 테스트")
    void testHandleStudentNotFoundException() throws Exception {
        mockMvc.perform(get("/student/123")
                .cookie(new Cookie("SESSION", "user")))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attribute("status", HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("StudentAlreadyExistsException 처리 테스트")
    void testHandleStudentAlreadyExistsException() throws Exception {
        Student newStudent = Student.create("user", "1234", "test", "a@a", 100, "good");
        mockMvc.perform(post("/student/register")
                .cookie(new Cookie("SESSION", newStudent.getId()))
                .param("id", newStudent.getId())
                .param("password", newStudent.getPassword())
                .param("name", newStudent.getName())
                .param("email", newStudent.getEmail())
                .param("score", String.valueOf(newStudent.getScore()))
                .param("comment", newStudent.getComment()))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attribute("status", HttpStatus.CONFLICT));
    }
}
