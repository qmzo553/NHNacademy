package com.nhnacademy.customerservice.domain.inquiry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class InquiryRequest {

    @Size(min = 2, max = 200)
    String title;

    @Size(min = 0, max = 40_000)
    String content;

    @NotBlank
    String category;
}
