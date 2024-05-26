package com.nhnacademy.customerservice.repository.answer;


import com.nhnacademy.customerservice.config.DataSourceConfig;
import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DataSourceConfig.class, AnswerRepositoryImpl.class, DbConnectionThreadLocal.class})
class AnswerRepositoryTest {

    @Autowired
    private DbConnectionThreadLocal dbConnectionThreadLocal;

    @Autowired
    private AnswerRepository answerRepository;
    Answer testAnswer;

    @BeforeEach
    public void setUp() {
        dbConnectionThreadLocal.initialize();
        testAnswer = Answer.create(1, "manager", "test");
        answerRepository.saveAnswer(testAnswer);
    }

    @AfterEach
    public void tearDown() {
        dbConnectionThreadLocal.setSqlError(true);
        dbConnectionThreadLocal.reset();
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
