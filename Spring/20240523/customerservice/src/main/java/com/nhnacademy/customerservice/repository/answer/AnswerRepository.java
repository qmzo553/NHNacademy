package com.nhnacademy.customerservice.repository.answer;

import com.nhnacademy.customerservice.domain.answer.Answer;

import java.util.Optional;

public interface AnswerRepository {

    Optional<Answer> getAnswerByInquiryId(long inquiryId);
    int saveAnswer(Answer answer);
    int countByInquiryId(long inquiryId);
}
