package com.nhnacademy.customerservice.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CustomerRequest {

    @Size(min = 8, max = 20)
    String id;

    @Size(min = 8, max = 20)
    String password;

    @NotBlank
    String name;

    @Min(0)
    int age;

    @Size(min = 10, max = 12)
    String phoneNumber;

    @Email
    String email;
}
