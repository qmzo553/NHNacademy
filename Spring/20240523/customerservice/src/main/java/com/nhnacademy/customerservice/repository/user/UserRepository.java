package com.nhnacademy.customerservice.repository.user;

import com.nhnacademy.customerservice.domain.user.User;

public interface UserRepository {

    User getUser(String id);
    boolean exists(String id);
    boolean matches(String id, String password);
}
