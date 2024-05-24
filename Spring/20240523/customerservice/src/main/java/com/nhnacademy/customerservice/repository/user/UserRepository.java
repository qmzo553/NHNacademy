package com.nhnacademy.customerservice.repository.user;

import com.nhnacademy.customerservice.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserByUserId(String userId);
    Optional<User> getUserByUserIdAndPassword(String userId, String password);
    int saveUser(User user);
    int countByUserId(String userId);
}
