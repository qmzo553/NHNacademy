package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.InquiryNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InquiryRepositoryImpl implements InquiryRepository {

    Map<String, List<Inquiry>> inquiryMap = new HashMap<>();

    @Override
    public boolean exists(String customerId) {
        if(customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return inquiryMap.containsKey(customerId);
    }

    @Override
    public List<Inquiry> getInquiryListByCustomerId(String customerId) {
        if(!exists(customerId)) {
            throw new InquiryNotFoundException();
        }

        return inquiryMap.get(customerId);
    }

    @Override
    public void saveInquiry(Inquiry inquiry, String customerId) {
        if(inquiry == null) {
            throw new IllegalArgumentException();
        }

        List<Inquiry> inquiryList = getInquiryListByCustomerId(customerId);
        inquiryList.add(inquiry);
        inquiryMap.put(customerId, inquiryList);
    }

    @Override
    public Inquiry getInquiryById(Long inquiryId) {
        Optional<Inquiry> inquiry = inquiryMap.values().stream()
                .flatMap(List::stream)
                .filter(Inquiry -> Inquiry.getInquiryId() == inquiryId)
                .findFirst();

        return inquiry.orElse(null);
    }
}
