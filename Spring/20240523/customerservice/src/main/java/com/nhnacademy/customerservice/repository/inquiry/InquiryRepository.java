package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;

import java.util.List;

public interface InquiryRepository {

    List<Inquiry> getInquiryListByCustomerId(String customerId);
    List<Inquiry> getInquiryListByCustomerIdAndCategory(String inquiryId, String category);
    List<Inquiry> getNoAnswerYet();
    void saveInquiry(Inquiry inquiry, String customerId);
    Inquiry getInquiryById(Long inquiryId);
}
