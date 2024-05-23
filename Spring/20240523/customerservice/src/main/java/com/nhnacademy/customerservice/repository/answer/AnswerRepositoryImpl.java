package com.nhnacademy.customerservice.repository.answer;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.exception.AnswerNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    Map<Long, Answer> answerMap = new HashMap<>();

    @Override
    public boolean exists(long inquiryId) {
        return answerMap.containsKey(inquiryId);
    }

    @Override
    public Answer getAnswer(long inquiryId) {
        if(!exists(inquiryId)) {
            throw new AnswerNotFoundException();
        }

        return answerMap.get(inquiryId);
    }

    @Override
    public Answer saveAnswer(Answer answer, long inquiryId) {
        if(answer == null) {
            throw new IllegalArgumentException();
        }

        return answerMap.put(inquiryId, answer);
    }
}
