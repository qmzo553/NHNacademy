package com.nhnacademy.customerservice.domain.inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Inquiry implements Comparable<Inquiry>{
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
    private String writerId;
    private Category category;
    private List<MultipartFile> files;
    private boolean answerStatus;

    public static Inquiry create(String title, String content, String writerId, Category category, List<MultipartFile> files) {
        return new Inquiry(++currentId, title, content, LocalDateTime.now(), writerId, category, files, false);
    }

    @Override
    public int compareTo(Inquiry other) {
        return this.createAt.compareTo(other.createAt);
    }
}
