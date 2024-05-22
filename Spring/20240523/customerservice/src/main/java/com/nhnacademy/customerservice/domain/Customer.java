package com.nhnacademy.customerservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends User {

    public Customer(String id, String password, String name, int age, String email) {
        super(id, password, name, age, email);
    }

    public static Customer create(String id, String password, String name, int age, String email) {
        return new Customer(id, password, name, age, email);
    }
}
