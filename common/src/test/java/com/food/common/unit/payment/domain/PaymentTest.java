package com.food.common.unit.payment.domain;

import com.food.common.mock.order.MockOrder;
import com.food.common.mock.payment.MockPayment;
import com.food.common.payment.domain.Payment;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.food.common.payment.utils.PaymentValidationFailureMessages.Payment.ORDER_CANNOT_BE_NULL;
import static com.food.common.payment.utils.PaymentValidationFailureMessages.Payment.STATUS_CANNOT_BE_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PaymentTest extends SuperValidationTests<Payment> {

    @Test
    void validateOrderInPayment() {
        Payment mockPaymentWithNullOrder = MockPayment.builder()
                .order(null)
                .build();

        Payment mockPaymentWithNormalOrder = MockPayment.builder()
                .order(MockOrder.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockPaymentWithNullOrder)).containsExactlyInAnyOrder(ORDER_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPaymentWithNormalOrder)).isEmpty()
        );
    }

    @Test
    void validateStatusInPayment() {
        Payment mockPaymentWithNullStatus = MockPayment.builder()
                .status(null)
                .build();

        Set<Payment> mockPaymentsWithEnumStatus = Arrays.stream(Payment.Status.values())
                .map(status -> MockPayment.builder().build())
                .collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(mockPaymentWithNullStatus)).containsExactlyInAnyOrder(STATUS_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPaymentsWithEnumStatus)).isEmpty()
        );
    }
}
