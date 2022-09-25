package com.company.paydaytrade.repository;

import com.company.paydaytrade.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    String expected = "ab";

    @Test
    void findUserByEmail() {
        User u = User.builder().cash(12D).email(expected).name("ali").password("123").build();
        userRepository.save(u);

        Optional<User> users = userRepository.findUserByEmail(expected);
        users.ifPresent(user -> assertThat(user.getName()).isEqualTo("ali"));
    }

    @Test
    void findUserById() {
    }
}