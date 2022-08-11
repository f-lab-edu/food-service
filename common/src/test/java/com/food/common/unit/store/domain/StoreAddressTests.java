package com.food.common.unit.store.domain;

import com.food.common.mock.store.MockStore;
import com.food.common.mock.store.MockStoreAddress;
import com.food.common.store.domain.StoreAddress;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.store.utils.StoreValidationFailureMessages.StoreAddress.*;
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
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNullStore)).containsExactlyInAnyOrder(STORE_CANNOT_BE_NULL),
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
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNullAddress)).containsExactlyInAnyOrder(ADDRESS_CANNOT_BE_NULL),
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
                        .containsExactlyInAnyOrder(formatLength(ADDRESS_DETAIL_HAS_TO_BE_BETWEEN_LENGTH, 150, exceededValue)),
                () -> assertThat(failureMessagesOf(mockStoreAddressWithNullAddressDetail)).isEmpty(),
                () -> assertThat(failureMessagesOf(mockStoreAddressWithBlankAddressDetail)).isEmpty()
        );
    }
}
