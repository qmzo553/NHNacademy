package com.nhnacademy.customerservice.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class File {

    private String fileName;
    private long inquiryId;

    public static File create(String fileName, long inquiryId) {
        return new File(fileName, inquiryId);
    }
}
