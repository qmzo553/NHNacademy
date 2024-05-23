package com.nhnacademy.customerservice.repository;

import com.nhnacademy.customerservice.domain.Customer;
import com.nhnacademy.customerservice.domain.User;

public interface UserRepository {

    User getUser(String id);
    boolean exists(String id);
    boolean matches(String id, String password);
}
