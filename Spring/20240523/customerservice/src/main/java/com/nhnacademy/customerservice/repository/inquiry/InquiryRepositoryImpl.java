package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.InquiryNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    Map<String, List<Inquiry>> inquiryMap = new HashMap<>();

    @Override
    public List<Inquiry> getInquiryListByCustomerId(String customerId) {
        List<Inquiry> inquiryList = inquiryMap.get(customerId);
        if(inquiryList == null) {
            return new ArrayList<>();
        }

        return inquiryMap.get(customerId);
    }

    @Override
    public void saveInquiry(Inquiry inquiry, String customerId) {
        if(inquiry == null) {
            throw new InquiryNotFoundException();
        }

        List<Inquiry> inquiryList = getInquiryListByCustomerId(customerId);
        inquiryList.add(inquiry);
        Collections.sort(inquiryList);
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

    @Override
    public List<Inquiry> getNoAnswerYet() {
        return inquiryMap.values().stream()
                .flatMap(List::stream)
                .filter(Inquiry -> !Inquiry.isAnswerStatus())
                .toList();
    }
}
