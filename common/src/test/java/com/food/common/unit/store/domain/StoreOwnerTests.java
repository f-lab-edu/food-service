package com.food.common.unit.store.domain;

import com.food.common.mock.store.MockStoreOwner;
import com.food.common.mock.user.MockUser;
import com.food.common.unit.SuperValidationTests;
import com.food.common.store.domain.StoreOwner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.food.common.store.utils.StoreValidationFailureMessages.StoreOwner.USER_CANNOT_BE_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreOwnerTests extends SuperValidationTests<StoreOwner> {

    @DisplayName("유저정보는 존재해야한다.")
    @Test
    void userShouldNotBeNull() {
        StoreOwner storeOwnerWithNullUser = MockStoreOwner.builder()
                .user(null)
                .build();

        StoreOwner storeOwnerWithPresentUser = MockStoreOwner.builder()
                .user(MockUser.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(storeOwnerWithNullUser)).containsExactlyInAnyOrder(USER_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(storeOwnerWithPresentUser)).isEmpty()
        );
    }
}
