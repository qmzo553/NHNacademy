package com.nhnacademy.customerservice.domain.user;

public class Customer extends User {

    public Customer(String id, String password, String name, int age, String phone, String email) {
        super(id, password, name, age, phone, email);
    }

    public static Customer create(String id, String password, String name, int age, String phone, String email) {
        return new Customer(id, password, name, age, phone, email);
    }
}
