package com.nhnacademy.customerservice.domain;

public class User {

    String id;
    String password;
    String name;
    int age;
    String email;

    public User(String id, String password, String name, int age, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
