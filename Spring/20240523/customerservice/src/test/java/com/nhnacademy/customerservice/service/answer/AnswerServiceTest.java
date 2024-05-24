package com.nhnacademy.customerservice.service.answer;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.repository.answer.AnswerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    AnswerRepository answerRepository = mock(AnswerRepository.class);
    AnswerService answerService;
    Answer testAnswer;

    @BeforeEach
    public void setUp() {
        answerService = new AnswerServiceImpl(answerRepository);
        testAnswer = Answer.create(1, "manager", "test");
    }

    @Test
    @DisplayName("get answer")
    void getAnswer() {
        when(answerRepository.getAnswerByInquiryId(anyLong())).thenReturn(Optional.of(testAnswer));
        answerService.getAnswer(1L);
        verify(answerRepository, times(1)).getAnswerByInquiryId(anyLong());
    }

    @Test
    @DisplayName("get answer exception")
    void getAnswerException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> answerService.getAnswer(-1L));
    }

    @Test
    @DisplayName("save answer")
    void save() {

    }
}
