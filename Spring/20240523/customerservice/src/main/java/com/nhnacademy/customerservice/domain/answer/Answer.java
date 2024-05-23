package com.nhnacademy.customerservice.domain.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Answer {

    private String content;
    private LocalDateTime createAt;
    private String managerId;

    public static Answer create(String content, String managerId) {
        return new Answer(content, LocalDateTime.now(), managerId);
    }
}
