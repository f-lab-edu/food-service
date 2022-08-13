package com.food.common.unit.order.domain;

import com.food.common.mock.order.MockOrderMenu;
import com.food.common.order.domain.OrderMenu;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.food.common.order.utils.OrderValidationFailureMessages.OrderMenu.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderMenuTest extends SuperValidationTests<OrderMenu> {

    @Test
    void validateNullPropertiesInOrderMenu() {
        OrderMenu mockOrderMenuWithNullProperties = MockOrderMenu.builder()
                .order(null)
                .menu(null)
                .amount(null)
                .count(null)
                .build();

        assertThat(failureMessagesOf(mockOrderMenuWithNullProperties))
                .containsExactlyInAnyOrder(ORDER_CANNOT_BE_NULL, MENU_CANNOT_BE_NULL, AMOUNT_CANNOT_BE_NULL, COUNT_CANNOT_BE_NULL);
    }

    @Test
    void validatePositivePropertiesInOrderMenu() {
        Set<Integer> negativeNumbers = Set.of(-1, -1000, -10000);

        Set<OrderMenu> mockOrderMenusWithNegativeNumbers = new HashSet<>();
        Set<String> failureMessages = new HashSet<>();
        negativeNumbers.forEach(negativeNumber -> {
            OrderMenu mockOrderMenuWithNullProperties = MockOrderMenu.builder()
                    .amount(negativeNumber)
                    .build();
            mockOrderMenusWithNegativeNumbers.add(mockOrderMenuWithNullProperties);
            failureMessages.add(formatPositive(AMOUNT_HAS_TO_BE_POSITIVE, negativeNumber));
        });

        assertThat(failureMessagesOf(mockOrderMenusWithNegativeNumbers)).isEqualTo(failureMessages);
    }
}
