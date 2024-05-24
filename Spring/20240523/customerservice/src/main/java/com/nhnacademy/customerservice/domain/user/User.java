package com.nhnacademy.customerservice.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
//    public enum Role {
//        USER, ADMIN
//    }

    private String id;
    private String password;
    private String name;
    private int age;
//    private Role role;
    private String phone;
    private String email;
}