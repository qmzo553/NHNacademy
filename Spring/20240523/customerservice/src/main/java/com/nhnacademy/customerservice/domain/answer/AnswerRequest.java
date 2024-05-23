package com.nhnacademy.customerservice.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AnswerRequest {

    @Size(min = 1, max = 40_000)
    String content;

    @NotBlank
    String managerId;
}
