package com.nhnacademy.customerservice.service.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.InquiryNotFoundException;
import com.nhnacademy.customerservice.repository.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    @Override
    public List<Inquiry> getInquiriesByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return inquiryRepository.getInquiriesByUserId(userId).reversed();
    }

    @Override
    public List<Inquiry> getInquiriesByUserIdAndCategory(String userId, String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Inquiry.Category categoryEnum = Inquiry.Category.valueOf(category);
        List<Inquiry> inquiries = getInquiriesByUserId(userId);
        return inquiries.stream()
                .filter(Inquiry -> Inquiry.getCategory().equals(categoryEnum))
                .toList()
                .reversed();
    }

    @Override
    public Inquiry getInquiryByInquiryId(long inquiryId) {
        if (inquiryId <= 0) {
            throw new IllegalArgumentException();
        }

        return inquiryRepository.getInquiryByInquiryId(inquiryId).orElseThrow(InquiryNotFoundException::new);
    }

    @Override
    public List<Inquiry> getNoAnswerYet() {
        return inquiryRepository.getInquiriesNoAnswerYet();
    }

    @Override
    public void saveInquiry(Inquiry inquiry) {
        if (inquiry == null) {
            throw new IllegalArgumentException();
        }

        inquiryRepository.saveInquiry(inquiry);
    }

    @Override
    public void updateInquiry(Inquiry inquiry) {
        if (inquiry == null) {
            throw new IllegalArgumentException();
        }

        inquiryRepository.updateInquiry(inquiry);
    }
}
