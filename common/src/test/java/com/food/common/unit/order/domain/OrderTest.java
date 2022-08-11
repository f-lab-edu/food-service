package com.food.common.unit.order.domain;

import com.food.common.mock.order.MockOrder;
import com.food.common.order.domain.Order;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.order.utils.OrderValidationFailureMessages.Order.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest extends SuperValidationTests<Order> {

    @Test
    void validateNullPropertiesInOrder() {
        Order mockOrderWithNullProperties = MockOrder.builder()
                .customer(null)
                .store(null)
                .amount(null)
                .status(null)
                .build();

        assertThat(failureMessagesOf(mockOrderWithNullProperties)).containsExactlyInAnyOrder(
                CUSTOMER_CANNOT_BE_NULL, STORE_CANNOT_BE_NULL, AMOUNT_CANNOT_BE_NULL, STATUS_CANNOT_BE_NULL);
    }

    @Test
    void validatePositivePropertiesInOrder() {
        int negativeAmount = -1000;
        Order mockOrderWithNegativeAmount = MockOrder.builder()
                .amount(negativeAmount)
                .build();

        assertThat(failureMessagesOf(mockOrderWithNegativeAmount))
                .containsExactlyInAnyOrder(formatPositive(AMOUNT_HAS_TO_BE_POSITIVE, negativeAmount));
    }

    @Test
    void validateLengthInOrder() {
        String longComment = "A".repeat(151);
        Order mockOrderWithLongComment = MockOrder.builder()
                .comment(longComment)
                .build();

        assertThat(failureMessagesOf(mockOrderWithLongComment))
                .containsExactlyInAnyOrder(formatLength(COMMENT_HAS_TO_BE_BETWEEN_LENGTH, 150, longComment));
    }
}
