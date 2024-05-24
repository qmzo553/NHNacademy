package com.nhnacademy.customerservice.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    public enum Role {
        USER, ADMIN
    }

    private String id;
    private String password;
    private String name;
    private int age;
    private Role role;
    private String phone;
    private String email;

    public static User createUser(String id, String password, String name, int age, String phone, String email) {
        return new User(id, password, name, age, Role.USER, phone, email);
    }

    public static User createAdmin(String id, String password, String name, int age, String phone, String email) {
        return new User(id, password, name, age, Role.ADMIN, phone, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }
}