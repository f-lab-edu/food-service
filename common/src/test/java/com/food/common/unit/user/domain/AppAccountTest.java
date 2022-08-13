package com.food.common.unit.user.domain;

import com.food.common.mock.user.MockAppAccount;
import com.food.common.mock.user.MockUser;
import com.food.common.unit.SuperValidationTests;
import com.food.common.user.domain.AppAccount;
import org.junit.jupiter.api.Test;

import static com.food.common.user.UserValidationFailureMessages.AppAccount.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AppAccountTest extends SuperValidationTests<AppAccount> {

    @Test
    void validateLoginIdInAppAccount() {
        String longLoginId = "A".repeat(45) + "@email.com";
        AppAccount mockAccountWithLongLoginId = MockAppAccount.builder()
                .loginId(longLoginId)
                .build();

        String loginIdNotInEmailFormat = "TestUserABC";
        AppAccount mockAccountWithLoginIdNotInEmailFormat = MockAppAccount.builder()
                .loginId(loginIdNotInEmailFormat)
                .build();

        AppAccount mockAccountWithNormalLoginId = MockAppAccount.builder()
                .loginId("TestUserABC@email.com")
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockAccountWithLongLoginId)).containsExactlyInAnyOrder(formatLength(LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING, 50, longLoginId)),
                () -> assertThat(failureMessagesOf(mockAccountWithLoginIdNotInEmailFormat)).containsExactlyInAnyOrder(formatEmail(LOGIN_ID_HAS_TO_BE_IN_EMAIL_FORMAT, loginIdNotInEmailFormat)),
                () -> assertThat(failureMessagesOf(mockAccountWithNormalLoginId)).isEmpty()
        );
    }

    @Test
    void validatePasswordInAppAccount() {
        AppAccount mockAccountWithBlankPassword = MockAppAccount.builder()
                .password(" ")
                .build();

        String longPassword = "A".repeat(101);
        AppAccount mockAccountWithLongPassword = MockAppAccount.builder()
                .password(longPassword)
                .build();

        AppAccount mockAccountWithNormalPassword = MockAppAccount.builder()
                .password("abcdABCDTestPassword1234")
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockAccountWithBlankPassword)).containsExactlyInAnyOrder(PASSWORD_CANNOT_BE_BLANK),
                () -> assertThat(failureMessagesOf(mockAccountWithLongPassword)).containsExactlyInAnyOrder(formatLength(PASSWORD_HAS_TO_BE_BETWEEN_LENGTH, 100, longPassword)),
                () -> assertThat(failureMessagesOf(mockAccountWithNormalPassword)).isEmpty()
        );
    }

    @Test
    void validateUserInAppAccount() {
        AppAccount mockAccountWithNullUser = MockAppAccount.builder()
                .user(null)
                .build();

        AppAccount mockAccountWithNormalUser = MockAppAccount.builder()
                .user(MockUser.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockAccountWithNullUser)).containsExactlyInAnyOrder(USER_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockAccountWithNormalUser)).isEmpty()
        );
    }
}
