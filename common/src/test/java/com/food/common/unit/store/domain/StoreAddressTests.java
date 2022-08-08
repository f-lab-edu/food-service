package com.food.common.unit.store.domain;

import com.food.common.mock.store.MockStore;
import com.food.common.mock.store.MockStoreAddress;
import com.food.common.store.domain.StoreAddress;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.store.utils.StoreValidationFailureMessage.StoreAddress.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreAddressTests extends SuperValidationTests<StoreAddress> {

    @Test
    void validateStoreInStoreAddress() {
        StoreAddress mockStoreAddressWithNullStore = MockStoreAddress.builder()
                .store(null)
                .build();

        StoreAddress mockStoreAddressWithNormalStore = MockStoreAddress.builder()
                .store(MockStore.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNullStore)).containsExactlyInAnyOrder(NOT_NULL_STORE),
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNormalStore)).isEmpty()
        );
    }

    @Test
    void validateAddressInStoreAddress() {
        StoreAddress mockStoreAddressWithNullAddress = MockStoreAddress.builder()
                .address(null)
                .build();

        StoreAddress mockStoreAddressWithNormalStore = MockStoreAddress.builder()
                .store(MockStore.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNullAddress)).containsExactlyInAnyOrder(NOT_NULL_ADDRESS),
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNormalStore)).isEmpty()
        );
    }

    @Test
    void validateAddressDetailInStoreAddress() {
        String exceededValue = "A".repeat(151);
        StoreAddress mockStoreAddressWithLongAddressDetail = MockStoreAddress.builder()
                .addressDetail(exceededValue)
                .build();

        StoreAddress mockStoreAddressWithNullAddressDetail = MockStoreAddress.builder()
                .addressDetail(null)
                .build();

        StoreAddress mockStoreAddressWithBlankAddressDetail = MockStoreAddress.builder()
                .addressDetail("")
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockStoreAddressWithLongAddressDetail))
                        .containsExactlyInAnyOrder(formatLength(BETWEEN_LENGTH_OF_ADDRESS_DETAIL, 150, 0, exceededValue)),
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNullAddressDetail)).isEmpty(),
                () -> assertThat(failureMessagesOf(mockStoreAddressWithBlankAddressDetail)).isEmpty()
        );
    }
}
