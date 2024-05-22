package com.nhnacademy.customerservice.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Manager extends User {

    public Manager(String id, String password, String name, int age, String email) {
        super(id, password, name, age, email);
    }

    public static Manager createManager(String id, String password, String name, int age, String email) {
        return new Manager(id, password, name, age, email);
    }
}
