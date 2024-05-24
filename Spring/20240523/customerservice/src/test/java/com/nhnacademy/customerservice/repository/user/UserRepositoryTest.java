package com.nhnacademy.customerservice.repository.user;

import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.junit.jupiter.api.*;

import java.util.Optional;

class UserRepositoryTest {

    UserRepository userRepository = new UserRepositoryImpl();
    User testUser;

    @BeforeEach
    void setUp() {
        DbConnectionThreadLocal.initialize();
        testUser = User.createUser("test", "123", "test", 10, "01012345678", "a@a");
        userRepository.saveUser(testUser);
    }

    @AfterEach
    void tearDown() {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("user 조회 by userId")
    void findByUserId() {
        Optional<User> userOptional = userRepository.getUserByUserId(testUser.getId());
        Assertions.assertTrue(userOptional.isPresent());
    }

    @Test
    @DisplayName("user 조회 by userId and userPassword")
    void findByUserIdAndUserPassword() {
        Optional<User> userOptional = userRepository.getUserByUserIdAndPassword(testUser.getId(), testUser.getPassword());
        Assertions.assertTrue(userOptional.isPresent());
    }

    @Test
    @DisplayName("user 등록")
    void save() {
        User newUser = User.createUser("new", "123", "test", 10, "01012345678", "aa@a");
        int result = userRepository.saveUser(newUser);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result),
                () -> Assertions.assertEquals(newUser, userRepository.getUserByUserId(newUser.getId()).get())
        );
    }

    @Test
    @DisplayName("user count")
    void count() {
        int result = userRepository.countByUserId(testUser.getId());
        Assertions.assertEquals(1, result);
    }
}
