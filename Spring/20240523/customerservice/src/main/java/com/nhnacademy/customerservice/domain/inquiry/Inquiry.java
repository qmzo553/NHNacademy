package com.nhnacademy.customerservice.domain.inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Inquiry {
    private static long currentId = 0;

    public enum Category {
        COMPLAINT,
        SUGGESTION,
        REFUND_EXCHANGE,
        PRAISE,
        OTHER
    }

    private long inquiryId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private Category category;
    private boolean answerStatus;

    public static Inquiry create(String title, String content, Category category) {
        return new Inquiry(++currentId, title, content, LocalDateTime.now(), category, false);
    }

}
