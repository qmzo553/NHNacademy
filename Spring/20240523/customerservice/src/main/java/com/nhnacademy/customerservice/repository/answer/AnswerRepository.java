package com.nhnacademy.customerservice.repository.answer;

import com.nhnacademy.customerservice.domain.answer.Answer;

public interface AnswerRepository {

    boolean exists(long inquiryId);
    Answer getAnswer(long inquiryId);
    Answer saveAnswer(Answer answer, long inquiryId);
}
