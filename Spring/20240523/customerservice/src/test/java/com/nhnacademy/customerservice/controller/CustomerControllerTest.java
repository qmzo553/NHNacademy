package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.domain.file.File;
import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.ValidationFailedException;
import com.nhnacademy.customerservice.service.answer.AnswerService;
import com.nhnacademy.customerservice.service.file.FileService;
import com.nhnacademy.customerservice.service.inquiry.InquiryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private InquiryService inquiryService;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private FileService fileService;

    private Inquiry testInquiry;
    private Answer testAnswer;
    private File testFile;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController(inquiryService, answerService, fileService)).build();

        testInquiry = Inquiry.create(1L, "test", "test", "user", Inquiry.Category.OTHER);
        testAnswer = Answer.create(1L, "manager", "test");
        testFile = File.create("test.jpeg", 1L);
    }

    @Test
    @DisplayName("index 출력 기본")
    void index() throws Exception {
        when(inquiryService.getInquiriesByUserId(anyString())).thenReturn(List.of(testInquiry));
        mockMvc.perform(get("/cs/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/index"));
    }

    @Test
    @DisplayName("index 출력 카테고리 검색")
    void categoryIndex() throws Exception {
        when(inquiryService.getInquiriesByUserId(anyString())).thenReturn(List.of(testInquiry));
        when(inquiryService.getInquiriesByUserIdAndCategory(anyString(), anyString())).thenReturn(List.of(testInquiry));
        mockMvc.perform(get("/cs/user/")
                        .param("category", "OTHER"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/index"));
        verify(inquiryService, times(1)).getInquiriesByUserIdAndCategory(anyString(), anyString());

        mockMvc.perform(get("/cs/user/")
                        .param("category", "ALL"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/index"));
        verify(inquiryService, times(1)).getInquiriesByUserId(anyString());
    }

    @Test
    @DisplayName("inquiry 작성 화면 출력")
    void inquiry() throws Exception {
        mockMvc.perform(get("/cs/inquiry/"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/inquiry"));
    }

    @Test
    @DisplayName("inquiry 등록")
    void inquiryPost() throws Exception {
        when(inquiryService.getLastInquiryId()).thenReturn(1L);
        MockMultipartFile file = new MockMultipartFile("files", "test.txt", "text/plain", "Test file content".getBytes());

        mockMvc.perform(multipart("/cs/inquiry/")
                        .file(file)
                        .param("title", testInquiry.getTitle())
                        .param("content", testInquiry.getContent())
                        .param("category", testInquiry.getCategory().name())
                        .cookie(new Cookie("SESSION", "user")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/user"));


    }

    @Test
    @DisplayName("inquiry 등록 exception")
    void inquiryException() throws Exception {
        Inquiry newInquiry = Inquiry.create(1L, "a", "a", "user", Inquiry.Category.OTHER);
        MockMultipartFile file = new MockMultipartFile("files", "test.txt", "text/plain", "Test file content".getBytes());
        Throwable th = catchThrowable(() -> mockMvc.perform(multipart("/cs/inquiry")
                .file(file)
                .param("title", newInquiry.getTitle())
                .param("content", newInquiry.getContent())
                .param("category", newInquiry.getCategory().name())
                .cookie(new Cookie("SESSION", "user"))));

        assertThat(th).isInstanceOf(ServletException.class).hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    @DisplayName("inquiry 자세히 보기")
    void inquiryDetail() throws Exception {
        when(inquiryService.getInquiryByInquiryId(anyLong())).thenReturn(testInquiry);
        when(fileService.getFilesByInquiryId(anyLong())).thenReturn(List.of(testFile));

        mockMvc.perform(get("/cs/inquiry/" + testInquiry.getInquiryId()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/inquiry_detail"));

        testInquiry.setAnswerStatus(true);
        when(answerService.getAnswer(anyLong())).thenReturn(testAnswer);
        mockMvc.perform(get("/cs/inquiry/" + testInquiry.getInquiryId()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/inquiry_detail"));
    }
}
