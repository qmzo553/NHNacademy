package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    private MockMvc mockMvc;
    private Student testStudnet;

    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        testStudnet = Student.create("test", "1234", "test", "a@a", 100, "good");
        when(studentRepository.getStudent(anyString())).thenReturn(testStudnet);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(studentRepository)).build();
    }

    @Test
    @DisplayName("학생 상세 조회")
    void testStudentView() throws Exception {
        when(studentRepository.exists(anyString())).thenReturn(true);
        String url = String.format("/student/%s", testStudnet.getId());
        mockMvc.perform(get(url)
                        .cookie(new Cookie("SESSION", "test")))
                .andExpect(status().isOk())
                .andExpect(view().name("studentView"));
    }

    @Test
    @DisplayName("학생 정보 수정 화면 출력")
    void testStudentModifyGet() throws Exception {
        when(studentRepository.exists(anyString())).thenReturn(true);
        String url = String.format("/student/%s/modify", testStudnet.getId());
        mockMvc.perform(get(url)
                        .cookie(new Cookie("SESSION", "test")))
                .andExpect(status().isOk())
                .andExpect(view().name("studentModify"));
    }

    @Test
    @DisplayName("학생 정보 수정 반영 화면 출력")
    void testStudentModifyPost() throws Exception {
        when(studentRepository.exists(anyString())).thenReturn(true);
        Student newStudent = Student.create("test", "1234", "new", "a@a", 100, "good");
        String url = String.format("/student/%s/modify", newStudent.getId());
        String redirectUrl = String.format("/student/%s", newStudent.getId());

        when(studentRepository.modify(
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyInt(),
                anyString())).thenReturn(newStudent);

        mockMvc.perform(post(url)
                        .param("id", newStudent.getId())
                        .param("password", newStudent.getPassword())
                        .param("name", newStudent.getName())
                        .param("email", newStudent.getEmail())
                        .param("score", String.valueOf(newStudent.getScore()))
                        .param("comment", newStudent.getComment()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(redirectUrl));
    }

    @Test
    @DisplayName("학생 정보 오류")
    void testStudentModifyException() throws Exception {
        when(studentRepository.exists(anyString())).thenReturn(true);
        Student newStudent = Student.create("test", "1234", "new", "aa", 120, "good");
        String url = String.format("/student/%s/modify", newStudent.getId());

        Throwable th = catchThrowable(() -> mockMvc.perform(post(url)
                        .param("id", newStudent.getId())
                        .param("password", newStudent.getPassword())
                        .param("name", newStudent.getName())
                        .param("email", newStudent.getEmail())
                        .param("score", String.valueOf(newStudent.getScore()))
                        .param("comment", newStudent.getComment())));

        assertThat(th).isInstanceOf(ServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    @DisplayName("잘못된 url 요청")
    void testPageException() throws Exception {
        String exceptionUrl = "/student/exception";
        mockMvc.perform(get(exceptionUrl))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attribute("status", HttpStatus.NOT_FOUND));
    }
}
