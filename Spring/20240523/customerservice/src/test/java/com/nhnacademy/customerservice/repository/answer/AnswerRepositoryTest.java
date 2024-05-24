package com.nhnacademy.customerservice.repository.answer;


import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.junit.jupiter.api.*;

import java.util.Optional;

class AnswerRepositoryTest {

    AnswerRepository answerRepository = new AnswerRepositoryImpl();
    Answer testAnswer;

    @BeforeEach
    public void setUp() {
        DbConnectionThreadLocal.initialize();
        testAnswer = Answer.create(1, "manager", "test");
        answerRepository.saveAnswer(testAnswer);
    }

    @AfterEach
    public void tearDown() {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("get answer by inquiryId")
    void getAnswerByInquiryId() {
        Optional<Answer> answerOptional = answerRepository.getAnswerByInquiryId(testAnswer.getInquiryId());
        Assertions.assertTrue(answerOptional.isPresent());
    }

    @Test
    @DisplayName("save answer")
    void saveAnswer() {
        int result = answerRepository.saveAnswer(testAnswer);
        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("count by inquiryId")
    void conutByInquiryId() {
        int result = answerRepository.countByInquiryId(testAnswer.getInquiryId());
        Assertions.assertEquals(1, result);
    }
}
