package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.InquiryNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    Map<String, List<Inquiry>> inquiryMap = new HashMap<>();

    public InquiryRepositoryImpl() {
        List<Inquiry> inquiryList = new ArrayList<>();
        inquiryList.add(Inquiry.create("테스트", "테스트", "user", Inquiry.Category.OTHER, new ArrayList<>()));
        inquiryMap.put("user", inquiryList);
    }

    @Override
    public List<Inquiry> getInquiryListByCustomerId(String customerId) {
        return inquiryMap.getOrDefault(customerId, new ArrayList<>());
    }

    @Override
    public List<Inquiry> getInquiryListByCustomerIdAndCategory(String customerId, String category) {
        Inquiry.Category categoryEnum = Inquiry.Category.valueOf(category);
        return getInquiryListByCustomerId(customerId).stream()
                .filter(Inquiry -> Inquiry.getCategory().equals(categoryEnum))
                .toList();
    }

    @Override
    public void saveInquiry(Inquiry inquiry, String customerId) {
        if (inquiry == null) {
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
