package com.nhnacademy.customerservice.repository.user;

import com.nhnacademy.customerservice.domain.user.Customer;
import com.nhnacademy.customerservice.domain.user.Manager;
import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    Map<String, User> userMap = new HashMap<>();

    public UserRepositoryImpl() {
        userMap.put("user", Customer.create("user", "123", "user", 10, "01012345678", "a@a"));
        userMap.put("manager", Manager.create("manager", "123", "manager", 10, "01012345678", "a@a"));
    }

    @Override
    public User getUser(String id) {
        if(!exists(id)) {
            throw new UserNotFoundException();
        }

        return userMap.get(id);
    }

    @Override
    public boolean exists(String id) {
        if(id == null || id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getUser(id))
                .map(User -> User.getPassword().equals(password))
                .orElse(false);
    }
}
