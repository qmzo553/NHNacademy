package com.nhnacademy.customerservice.service.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;

import java.util.List;

public interface InquiryService {

    List<Inquiry> getInquiriesByUserId(String userId);
    List<Inquiry> getInquiriesByUserIdAndCategory(String userId, String category);
    Inquiry getInquiryByInquiryId(long inquiryId);
    List<Inquiry> getNoAnswerYet();
    void saveInquiry(Inquiry inquiry);
}
