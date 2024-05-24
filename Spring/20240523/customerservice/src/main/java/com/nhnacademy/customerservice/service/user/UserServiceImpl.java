package com.nhnacademy.customerservice.service.user;

import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.exception.UserNotFoundException;
import com.nhnacademy.customerservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(String userId) {
        if(userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(userRepository.countByUserId(userId) <= 0) {
            throw new UserNotFoundException();
        }

        return userRepository.getUserByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User doLogin(User user) {
        return userRepository.getUserByUserIdAndPassword(user.getId(), user.getPassword())
                .orElseThrow(UserNotFoundException::new);
    }
}
