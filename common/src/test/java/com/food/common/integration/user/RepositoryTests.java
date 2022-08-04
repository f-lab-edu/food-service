package com.food.common.integration.user;

import com.food.common.integration.SuperIntegrationTest;
import com.food.common.user.domain.User;
import com.food.common.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RepositoryTests extends SuperIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("User가 정상적으로 저장된다")
    @Test
    void shouldSaveUser() {
        User userA = User.create("userA");
        User savedUser = userRepository.save(userA);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @DisplayName("Nickname이 공백인 User를 저장할 경우 유효성 검증에 실패한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void shouldThrowException_whenSaveUserWithBlankNickname(String nickname) {
        User userA = User.create(nickname);

        assertThatThrownBy(() -> userRepository.save(userA))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
