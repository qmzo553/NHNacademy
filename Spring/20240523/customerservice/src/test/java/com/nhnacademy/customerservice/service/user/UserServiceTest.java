package com.nhnacademy.customerservice.service.user;

import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.exception.UserNotFoundException;
import com.nhnacademy.customerservice.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserRepository userRepository = mock(UserRepository.class);
    UserService userService;
    User testUser;


    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
        testUser = User.createUser("test", "123", "test", 10, "01012345678", "a@a");
    }

    @Test
    @DisplayName("get user")
    void get() {
        when(userRepository.getUserByUserId(anyString())).thenReturn(Optional.of(testUser));
        when(userRepository.countByUserId(anyString())).thenReturn(1);
        userService.getUser(testUser.getId());
        Mockito.verify(userRepository, Mockito.times(1)).getUserByUserId(anyString());
    }

    @Test
    @DisplayName("login")
    void login() {
        when(userRepository.getUserByUserIdAndPassword(anyString(), anyString())).thenReturn(Optional.of(testUser));
        boolean user = userService.doLogin(testUser.getId(), testUser.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).getUserByUserIdAndPassword(anyString(), anyString());
        Assertions.assertTrue(user);

        assertThrows(IllegalArgumentException.class, () -> userService.doLogin(null, null));
    }

    @Test
    @DisplayName("fail get user with exception")
    void exception() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUser(""));
        assertThrows(UserNotFoundException.class, () -> userService.getUser("123"));
    }
}
