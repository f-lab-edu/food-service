package com.food.common.unit.user.domain;

import com.food.common.mock.user.MockSocialAccount;
import com.food.common.mock.user.MockUser;
import com.food.common.unit.SuperValidationTests;
import com.food.common.user.domain.SocialAccount;
import org.junit.jupiter.api.Test;

import static com.food.common.user.UserValidationFailureMessages.AppAccount.USER_CANNOT_BE_NULL;
import static com.food.common.user.UserValidationFailureMessages.SocialAccount.LOGIN_ID_CANNOT_BE_BLANK;
import static com.food.common.user.UserValidationFailureMessages.SocialAccount.LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SocialAccountTest extends SuperValidationTests<SocialAccount> {

    @Test
    void validateLoginIdInSocialAccount() {
        SocialAccount mockAccountWithBlankLoginId = MockSocialAccount.builder()
                .loginId(" ")
                .build();

        String longLoginId = "A".repeat(51);
        SocialAccount mockAccountWithLongLoginId = MockSocialAccount.builder()
                .loginId(longLoginId)
                .build();

        SocialAccount mockAccountWithNormalLoginId = MockSocialAccount.builder()
                .loginId("test_user_1234")
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockAccountWithBlankLoginId)).containsExactlyInAnyOrder(LOGIN_ID_CANNOT_BE_BLANK),
                () -> assertThat(failureMessagesOf(mockAccountWithLongLoginId)).containsExactlyInAnyOrder(formatLength(LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING, 50, longLoginId)),
                () -> assertThat(failureMessagesOf(mockAccountWithNormalLoginId)).isEmpty()
        );
    }

    @Test
    void validateUserInSocialAccount() {
        SocialAccount mockAccountWithNullUser = MockSocialAccount.builder()
                .user(null)
                .build();

        SocialAccount mockAccountWithNormalUser = MockSocialAccount.builder()
                .user(MockUser.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockAccountWithNullUser)).containsExactlyInAnyOrder(USER_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockAccountWithNormalUser)).isEmpty()
        );
    }
}
