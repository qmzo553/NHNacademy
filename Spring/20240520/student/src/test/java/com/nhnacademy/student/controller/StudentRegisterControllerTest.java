package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentRegisterController.class)
class StudentRegisterControllerTest {

    private MockMvc mockMvc;
    private Student testStudnet;

    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        testStudnet = Student.create("test", "1234", "test", "a@a", 100, "good");
        when(studentRepository.register(anyString(), anyString(), anyString(), anyString(), anyInt(), anyString())).thenReturn(testStudnet);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentRegisterController(studentRepository)).build();
    }

    @Test
    @DisplayName("학생 등록 화면 표시")
    void testStudentRegisterGet() throws Exception {
        mockMvc.perform(get("/student/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentRegister"));
    }

    @Test
    @DisplayName("학생 등록 처리")
    void testRegisterStudent() throws Exception {
        mockMvc.perform(post("/student/register")
                        .param("id", testStudnet.getId())
                        .param("password", testStudnet.getPassword())
                        .param("name", testStudnet.getName())
                        .param("email", testStudnet.getEmail())
                        .param("score", String.valueOf(testStudnet.getScore()))
                        .param("comment", testStudnet.getComment()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @DisplayName("유효성 검사 실패 시 예외 처리")
    void testRegisterStudentValidationFailed() throws Exception {
        Student newStudent = Student.create("test", "1234", "test", "aa", 100, "good");
        Throwable th = catchThrowable(() -> mockMvc.perform(post("/student/register")
                .param("id", newStudent.getId())
                .param("password", newStudent.getPassword())
                .param("name", newStudent.getName())
                .param("email", newStudent.getEmail())
                .param("score", String.valueOf(newStudent.getScore()))
                .param("comment", newStudent.getComment())));

        assertThat(th).isInstanceOf(ServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }
}
