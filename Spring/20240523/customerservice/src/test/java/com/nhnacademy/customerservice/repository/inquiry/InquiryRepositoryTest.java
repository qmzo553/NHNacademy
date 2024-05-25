package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

class InquiryRepositoryTest {

    private  InquiryRepository inquiryRepository;

    Inquiry testInquiry;

    @BeforeEach
    void setUp() {
        DbConnectionThreadLocal.initialize();
        inquiryRepository = new InquiryRepositoryImpl();
        long inquiryId = inquiryRepository.getLastInquiryId();
        testInquiry = inquiryRepository.getInquiryByInquiryId(inquiryId).get();
    }

    @AfterEach
    void tearDown() {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("get inquiries by user id")
    void getInquiriesByUserId() {
        List<Inquiry> inquiryList  = inquiryRepository.getInquiriesByUserId(testInquiry.getWriterId());
        Assertions.assertEquals(1, inquiryList.size());
    }

    @Test
    @DisplayName("get inquiry by inquiry id")
    void getInquiryByInquiryId() {
        Optional<Inquiry> inquiryOptional = inquiryRepository.getInquiryByInquiryId(testInquiry.getInquiryId());
        Assertions.assertTrue(inquiryOptional.isPresent());
    }

    @Test
    @DisplayName("get inquiries no answer yet")
    void getInquiriesNoAnswer() {
        List<Inquiry> inquiryList = inquiryRepository.getInquiriesNoAnswerYet();
        Assertions.assertEquals(1, inquiryList.size());
    }

    @Test
    @DisplayName("save inquiry")
    void save() {
        Inquiry newInquiry = Inquiry.createAuto("test", "test", "user", Inquiry.Category.OTHER);
        int result = inquiryRepository.saveInquiry(newInquiry);
        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("get last inquiry id")
    void getLastInquiryId() {
        long result = inquiryRepository.getLastInquiryId();
        Assertions.assertEquals(1, result);
    }
}
