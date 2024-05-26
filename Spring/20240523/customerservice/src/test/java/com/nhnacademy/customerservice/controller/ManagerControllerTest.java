package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.ValidationFailedException;
import com.nhnacademy.customerservice.service.answer.AnswerService;
import com.nhnacademy.customerservice.service.file.FileService;
import com.nhnacademy.customerservice.service.inquiry.InquiryService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ManagerControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private InquiryService inquiryService;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private FileService fileService;

    private Inquiry testInquiry;
    private Answer testAnswer;

    @BeforeEach
    void setUp(@Autowired WebApplicationContext context) {
        testInquiry = Inquiry.create(1L, "test", "test", "user", Inquiry.Category.OTHER);
        testAnswer = Answer.create(1L, "manager", "test");
        mockMvc = MockMvcBuilders.standaloneSetup(new ManagerController(inquiryService, answerService, fileService)).build();
    }

    @Test
    @DisplayName("index 화면 출력")
    void index() throws Exception {
        mockMvc.perform(get("/cs/admin/"))
                .andExpect(status().isOk())
                .andExpect(view().name("manager/index"));
    }

    @Test
    @DisplayName("문의 답변 폼 출력")
    void answer() throws Exception {
        mockMvc.perform(get("/cs/admin/answer/" + testInquiry.getInquiryId()))
                .andExpect(status().isOk())
                .andExpect(view().name("manager/inquiry_answer"));
    }

    @Test
    @DisplayName("답변 처리")
    void answerPost() throws Exception {
        when(inquiryService.getInquiryByInquiryId(anyLong())).thenReturn(testInquiry);
        mockMvc.perform(post("/cs/admin/answer/")
                        .param("content", testAnswer.getContent())
                        .param("managerId", testAnswer.getManagerId())
                        .param("inquiryId", String.valueOf(testInquiry.getInquiryId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin/"));
    }

    @Test
    @DisplayName("답변 처리 exception")
    void answerPostException() throws Exception {
        Throwable th = catchThrowable(() -> mockMvc.perform(post("/cs/admin/answer/")
                .param("content", "")
                .param("managerId", "")
                .param("inquiryId", String.valueOf(testInquiry.getInquiryId()))));

        assertThat(th).isInstanceOf(ServletException.class).hasCauseInstanceOf(ValidationFailedException.class);
    }
}
