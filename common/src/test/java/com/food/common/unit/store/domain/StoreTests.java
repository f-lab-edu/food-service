package com.food.common.unit.store.domain;

import com.food.common.mock.store.MockStore;
import com.food.common.store.domain.Store;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.food.common.store.utils.StoreValidationFailureMessages.Store.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreTests extends SuperValidationTests<Store> {

    @DisplayName("상호명은 비어있거나 null일 수 없다")
    @Test
    void storeNameShouldBePresent() {
        Store mockStoreWithNullName = MockStore.builder()
                .name(null)
                .build();

        Store mockStoreWithEmptyName = MockStore.builder()
                .name("")
                .build();

        Store mockStoreWithBlankName = MockStore.builder()
                .name("  ")
                .build();

        Store mockStoreWithName = MockStore.builder()
                .name("StoreNameAA")
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockStoreWithNullName)).containsExactlyInAnyOrder(STORE_NAME_CANNOT_BE_BLANK),
                () -> assertThat(failureMessagesOf(mockStoreWithEmptyName)).containsExactlyInAnyOrder(STORE_NAME_CANNOT_BE_BLANK),
                () -> assertThat(failureMessagesOf(mockStoreWithBlankName)).containsExactlyInAnyOrder(STORE_NAME_CANNOT_BE_BLANK),
                () -> assertThat(failureMessagesOf(mockStoreWithName)).isEmpty()
        );
    }

    @DisplayName("사장님 정보는 존재해야한다.")
    @Test
    void storeOwnerShouldBePresent() {
        Store mockStoreWithNullOwner = MockStore.builder()
                .owner(null)
                .build();

        assertThat(failureMessagesOf(mockStoreWithNullOwner))
                .containsExactlyInAnyOrder(STORE_OWNER_CANNOT_BE_NULL);
    }

    @DisplayName("최소주문금액은 양수이며 필수값이다")
    @Test
    void validateMinOrderAmountInStore() {
        Store mockStoreWithNullMinOrderAmount = MockStore.builder()
                .minOrderAmount(null)
                .build();

        int negativeValue = -10;
        Store mockStoreWithNegativeMinOrderAmount = MockStore.builder()
                .minOrderAmount(negativeValue)
                .build();

        int positiveValue = 1000;
        Store mockStoreWithPositiveMinOrderAmount = MockStore.builder()
                .minOrderAmount(positiveValue)
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockStoreWithNullMinOrderAmount)).containsExactlyInAnyOrder(MIN_ORDER_AMOUNT_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockStoreWithNegativeMinOrderAmount)).containsExactlyInAnyOrder(
                        formatPositive(MIN_ORDER_AMOUNT_HAS_TO_BE_POSITIVE, negativeValue)),
                () -> assertThat(failureMessagesOf(mockStoreWithPositiveMinOrderAmount)).isEmpty()
        );
    }

    @DisplayName("운영상태는 null일 수 없다.")
    @Test
    void validateStatusInStore() {
        Store mockStoreWithNullStatus = MockStore.builder()
                .status(null)
                .build();

        Set<Store> mockStoresWithEnumTypeStatus = Arrays.stream(Store.OpenStatus.values()).map(
                status -> MockStore.builder()
                        .status(status)
                        .build()
        ).collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(mockStoreWithNullStatus)).containsExactlyInAnyOrder(OPEN_STATUS_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockStoresWithEnumTypeStatus)).isEmpty()
        );
    }
}
