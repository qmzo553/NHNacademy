package com.nhnacademy.customerservice.domain;

public class User {

    String id;
    String password;
    String name;
    int age;
    String phoneNumber;
    String email;

    public User(String id, String password, String name, int age, String phoneNumber, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
