package com.food.common.unit.user.domain;

import com.food.common.mock.user.MockUser;
import com.food.common.unit.SuperValidationTests;
import com.food.common.user.domain.User;
import org.junit.jupiter.api.Test;

import static com.food.common.user.UserValidationFailureMessages.User.NICKNAME_HAS_TO_BE_BETWEEN_LENGTH;
import static com.food.common.user.UserValidationFailureMessages.User.NICKNAME_CANNOT_BLANK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserTests extends SuperValidationTests<User> {

    @Test
    void validateNicknameInUser() {
        User mockUserWithNullNickname = MockUser.builder()
                .nickname(null)
                .build();

        User mockUserWithBlankNickname = MockUser.builder()
                .nickname(" ")
                .build();

        String longName = "A".repeat(31);
        User mockUserWithLongNickname = MockUser.builder()
                .nickname(longName)
                .build();

        User mockUserWithNickname = MockUser.builder()
                .nickname("normal_userA")
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockUserWithNullNickname)).containsExactlyInAnyOrder(NICKNAME_CANNOT_BLANK),
                () -> assertThat(failureMessagesOf(mockUserWithBlankNickname)).containsExactlyInAnyOrder(NICKNAME_CANNOT_BLANK),
                () -> assertThat(failureMessagesOf(mockUserWithLongNickname)).containsExactlyInAnyOrder(formatLength(NICKNAME_HAS_TO_BE_BETWEEN_LENGTH, 30, longName)),
                () -> assertThat(failureMessagesOf(mockUserWithNickname)).isEmpty()
        );
    }
}
