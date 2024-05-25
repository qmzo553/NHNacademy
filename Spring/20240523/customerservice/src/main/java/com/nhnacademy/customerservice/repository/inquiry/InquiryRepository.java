package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository {

    List<Inquiry> getInquiriesByUserId(String userId);
    Optional<Inquiry> getInquiryByInquiryId(Long inquiryId);
    List<Inquiry> getInquiriesNoAnswerYet();
    int saveInquiry(Inquiry inquiry);
    long getLastInquiryId();
}
