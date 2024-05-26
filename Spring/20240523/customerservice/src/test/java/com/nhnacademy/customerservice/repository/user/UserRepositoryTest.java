package com.nhnacademy.customerservice.repository.user;

import com.nhnacademy.customerservice.config.DataSourceConfig;
import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DataSourceConfig.class, UserRepositoryImpl.class, DbConnectionThreadLocal.class})
class UserRepositoryTest {

    @Autowired
    private DbConnectionThreadLocal dbConnectionThreadLocal;

    @Autowired
    private UserRepository userRepository;
    User testUser;

    @BeforeEach
    void setUp() {
        dbConnectionThreadLocal.initialize();
        testUser = User.createUser("test", "123", "user", 11, "01012341234", "c@c");
        userRepository.saveUser(testUser);
    }

    @AfterEach
    void tearDown() {
        dbConnectionThreadLocal.setSqlError(true);
        dbConnectionThreadLocal.reset();
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
