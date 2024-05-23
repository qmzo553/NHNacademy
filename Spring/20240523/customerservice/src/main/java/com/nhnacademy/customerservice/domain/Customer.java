package com.nhnacademy.customerservice.domain;

public class Customer extends User {

    public Customer(String id, String password, String name, int age, String phoneNumber, String email) {
        super(id, password, name, age, phoneNumber, email);
    }

    public static Customer create(String id, String password, String name, int age, String phoneNumber, String email) {
        return new Customer(id, password, name, age, phoneNumber, email);
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
