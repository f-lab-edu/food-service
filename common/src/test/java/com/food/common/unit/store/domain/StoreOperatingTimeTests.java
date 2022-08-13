package com.food.common.unit.store.domain;

import com.food.common.mock.store.MockStore;
import com.food.common.mock.store.MockStoreOperatingTime;
import com.food.common.store.domain.StoreOperatingTime;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.food.common.store.utils.StoreValidationFailureMessages.StoreOperatingTime.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreOperatingTimeTests extends SuperValidationTests<StoreOperatingTime> {

    @DisplayName("store는 null일 수 없다")
    @Test
    void validateStoreInStoreOperatingTime() {
        StoreOperatingTime operatingTimeWithNullStore = MockStoreOperatingTime.builder()
                .store(null)
                .build();

        StoreOperatingTime operatingTimeWithPresentStore = MockStoreOperatingTime.builder()
                .store(MockStore.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(operatingTimeWithNullStore)).containsExactlyInAnyOrder(STORE_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(operatingTimeWithPresentStore)).isEmpty()
        );
    }

    @DisplayName("week는 null일 수 없다")
    @Test
    void validateWeekInStoreOperatingTime() {
        StoreOperatingTime operatingTimeWithNullStore = MockStoreOperatingTime.builder()
                .week(null)
                .build();

        Set<StoreOperatingTime> operatingTimesWithEnumTypeWeek = Arrays.stream(StoreOperatingTime.Week.values())
                .map(w -> MockStoreOperatingTime.builder()
                        .week(w)
                        .build())
                .collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(operatingTimeWithNullStore)).containsExactlyInAnyOrder(WEEK_CANNOT_NULL),
                () -> assertThat(failureMessagesOf(operatingTimesWithEnumTypeWeek)).isEmpty()
        );
    }

    @DisplayName("opening time과 closing time은 null일 수 없다")
    @Test
    void validateOpeningTimeInStoreOperatingTime() {
        StoreOperatingTime operatingTimeWithNullOpeningAndClosingTime = MockStoreOperatingTime.builder()
                .openingTime(null)
                .closingTime(null)
                .build();

        StoreOperatingTime operatingTimeWithLocalTimeOpeningAndClosingTime = MockStoreOperatingTime.builder()
                .openingTime(LocalTime.of(10, 30))
                .closingTime(LocalTime.of(22, 30))
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(operatingTimeWithNullOpeningAndClosingTime)).containsExactlyInAnyOrder(OPENING_TIME_CANNOT_NULL, CLOSING_TIME_CANNOT_NULL),
                () -> assertThat(failureMessagesOf(operatingTimeWithLocalTimeOpeningAndClosingTime)).isEmpty()
        );
    }
}
