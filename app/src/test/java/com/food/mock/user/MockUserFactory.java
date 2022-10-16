package com.food.mock.user;

import com.food.common.user.domain.User;
import com.food.common.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockUserFactory {
    @Autowired
    private UserRepository userRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public User user() {
        User user = MockUser.builder()
                .build();

        return userRepository.save(user);
    }

    public User user(String nickname) {
        User user = MockUser.builder()
                .nickname(nickname)
                .build();

        return userRepository.save(user);
    }
}
