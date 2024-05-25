package com.nhnacademy.customerservice.service.user;

import com.nhnacademy.customerservice.domain.user.User;

public interface UserService {

    User getUser(String userId);
    boolean doLogin(String userId, String password);
}
