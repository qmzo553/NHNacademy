package com.nhnacademy.student.domain;

import lombok.Value;

@Value
public class StudentRegisterRequest {
    String id;
    String password;
    String name;
}
