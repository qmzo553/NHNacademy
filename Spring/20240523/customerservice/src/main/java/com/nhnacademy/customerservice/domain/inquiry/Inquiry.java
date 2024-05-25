package com.nhnacademy.customerservice.domain.inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Inquiry implements Comparable<Inquiry>{

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
    private String writerId;
    private Category category;

    public static Inquiry create(Long inquiryId, String title, String content, String writerId, Category category) {
        return new Inquiry(inquiryId, title, content, LocalDateTime.now(), writerId, category);
    }

    public static Inquiry createAuto(String title, String content, String writerId, Category category) {
        return new Inquiry(0, title, content, LocalDateTime.now(), writerId, category);
    }

    @Override
    public int compareTo(Inquiry other) {
        return this.createAt.compareTo(other.createAt);
    }
}
