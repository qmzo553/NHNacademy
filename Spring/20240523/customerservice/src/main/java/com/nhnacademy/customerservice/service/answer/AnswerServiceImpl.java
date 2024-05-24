package com.nhnacademy.customerservice.service.answer;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.exception.AnswerNotFoundException;
import com.nhnacademy.customerservice.repository.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer getAnswer(Long inquiryId) {
        if(inquiryId <= 0) {
            throw new IllegalArgumentException();
        }

        return answerRepository.getAnswerByInquiryId(inquiryId).orElseThrow(AnswerNotFoundException::new);
    }

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.saveAnswer(answer);
    }
}
