package com.nhnacademy.customerservice.service.answer;

import com.nhnacademy.customerservice.domain.answer.Answer;

public interface AnswerService {

    Answer getAnswer(Long inquiryId);
    void saveAnswer(Answer answer);
}
