package com.food.common.unit.store.domain;

import com.food.common.mock.store.MockStoreOwner;
import com.food.common.mock.MockUser;
import com.food.common.unit.SuperValidationTests;
import com.food.common.user.domain.StoreOwner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.food.common.store.utils.StoreValidationFailureMessage.StoreOwner.NOT_NULL_USER;
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
                () -> assertThat(failureMessagesOf(storeOwnerWithNullUser)).containsExactlyInAnyOrder(NOT_NULL_USER),
                () -> assertThat(failureMessagesOf(storeOwnerWithPresentUser)).isEmpty()
        );
    }
}
