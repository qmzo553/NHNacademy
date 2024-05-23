package com.nhnacademy.customerservice.domain.user;

public class Manager extends User {

    public Manager(String id, String password, String name, int age, String phoneNumber, String email) {
        super(id, password, name, age, phoneNumber, email);
    }

    public static Manager create(String id, String password, String name, int age, String phoneNumber, String email) {
        return new Manager(id, password, name, age, phoneNumber, email);
    }
}
