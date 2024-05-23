package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;

import java.util.List;

public interface InquiryRepository {

    boolean exists(String customerId);
    List<Inquiry> getInquiryListByCustomerId(String customerId);
    void saveInquiry(Inquiry inquiry, String customerId);
    Inquiry getInquiryById(Long inquiryId);
}
