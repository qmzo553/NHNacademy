package com.nhnacademy.customerservice.service.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.repository.inquiry.InquiryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.nhnacademy.customerservice.domain.inquiry.Inquiry.Category.OTHER;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InquiryServiceTest {

    InquiryRepository inquiryRepository = mock(InquiryRepository.class);
    InquiryService inquiryService;
    Inquiry testInquiry;

    @BeforeEach
    void setUp() {
        inquiryService = new InquiryServiceImpl(inquiryRepository);
        testInquiry = Inquiry.create(1L,"test", "test", "user", OTHER);
    }

    @Test
    @DisplayName("get inquiries by user id")
    void getInquiriesByUserId() {
        when(inquiryRepository.getInquiriesByUserId(anyString())).thenReturn(List.of(testInquiry));
        inquiryService.getInquiriesByUserId(testInquiry.getWriterId());
        verify(inquiryRepository, times(1)).getInquiriesByUserId(anyString());


        assertThrows(IllegalArgumentException.class, () -> inquiryService.getInquiriesByUserId(""));
    }

    @Test
    @DisplayName("get inquiries by user id and category")
    void getInquiriesByUserIdAndCategory() {
        when(inquiryRepository.getInquiriesByUserId(anyString())).thenReturn(List.of(testInquiry));
        List<Inquiry> inquiries = inquiryService.getInquiriesByUserIdAndCategory("user", "OTHER");
        verify(inquiryRepository, times(1)).getInquiriesByUserId(anyString());
        Assertions.assertEquals(1, inquiries.size());

        assertThrows(IllegalArgumentException.class, () -> inquiryService.getInquiriesByUserIdAndCategory("", ""));
    }

    @Test
    @DisplayName("get inquiry by inquiryId")
    void getInquiryByInquiryId() {
        when(inquiryRepository.getInquiryByInquiryId(anyLong())).thenReturn(Optional.of(testInquiry));
        inquiryService.getInquiryByInquiryId(testInquiry.getInquiryId());
        verify(inquiryRepository, times(1)).getInquiryByInquiryId(anyLong());

        assertThrows(IllegalArgumentException.class, () -> inquiryService.getInquiryByInquiryId(-1));
    }

    @Test
    @DisplayName("get inquiries no answer yet")
    void getInquiriesNoAnswer() {
        when(inquiryRepository.getInquiriesNoAnswerYet()).thenReturn(List.of(testInquiry));
        inquiryService.getNoAnswerYet();
        verify(inquiryRepository, times(1)).getInquiriesNoAnswerYet();
    }

    @Test
    @DisplayName("get last inquiry id")
    void getLastInquiryId() {
        when(inquiryRepository.getLastInquiryId()).thenReturn(2L);
        inquiryService.getLastInquiryId();
        verify(inquiryRepository, times(1)).getLastInquiryId();
    }

    @Test
    @DisplayName("save inquiry")
    void saveInquiry() {
        when(inquiryRepository.saveInquiry(any(Inquiry.class))).thenReturn(1);
        inquiryService.saveInquiry(testInquiry);
        verify(inquiryRepository, times(1)).saveInquiry(any());

        assertThrows(IllegalArgumentException.class, () -> inquiryService.saveInquiry(null));
    }

    @Test
    @DisplayName("update inquiry")
    void updateInquiry() {
        when(inquiryRepository.updateInquiry(any(Inquiry.class))).thenReturn(1);
        inquiryService.updateInquiry(testInquiry);
        verify(inquiryRepository, times(1)).updateInquiry(any());

        assertThrows(IllegalArgumentException.class, () -> inquiryService.updateInquiry(null));
    }
}
