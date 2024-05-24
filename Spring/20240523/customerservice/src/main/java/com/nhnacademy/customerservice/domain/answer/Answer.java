package com.nhnacademy.customerservice.domain.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Answer {

    private Long inquiryId;
    private String managerId;
    private String content;
    private LocalDateTime createAt;


    public static Answer create(long inquiryId, String managerId, String content) {
        return new Answer(inquiryId, managerId, content, LocalDateTime.now());
    }
}
