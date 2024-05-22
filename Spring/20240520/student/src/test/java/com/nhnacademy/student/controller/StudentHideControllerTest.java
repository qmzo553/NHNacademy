package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentHideController.class)
public class StudentHideControllerTest {

    private MockMvc mockMvc;
    private Student testStudnet;

    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        testStudnet = Student.create("test", "1234", "test", "a@a", 100, "good");
        when(studentRepository.getStudent(anyString())).thenReturn(testStudnet);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentHideController(studentRepository)).build();
    }

    @Test
    @DisplayName("학생 정보 조회 - 성적 숨김")
    void testStudentHideScore() throws Exception {
        mockMvc.perform(get("/student/test")
                        .param("hideScore", "yes"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentView"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attributeExists("hideScore"))
                .andExpect(model().attribute("hideScore", "yes"));
    }

    @Test
    @DisplayName("학생 정보 조회 - 성적 숨기지 않음")
    void testStudentShowScore() throws Exception {
        mockMvc.perform(get("/student/test")
                        .param("hideScore", "no"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentView"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attributeExists("hideScore"))
                .andExpect(model().attribute("hideScore", "no"));
    }
}
