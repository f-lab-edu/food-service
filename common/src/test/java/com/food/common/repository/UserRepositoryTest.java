package com.food.common.repository;

import com.food.common.common.domain.Point;
import com.food.common.user.domain.User;
import com.food.common.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest extends CommonRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() {
        User user = new User("userA", "passwordABCD", "userA", new Point(100));
        userRepository.save(user);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(4L);
    }
}
