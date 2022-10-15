package com.food.common.unit.payment.domain;

import com.food.common.mock.payment.MockPayment;
import com.food.common.mock.payment.MockPaymentLog;
import com.food.common.mock.user.MockPoint;
import com.food.common.payment.domain.PaymentLog;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.food.common.payment.utils.PaymentValidationFailureMessages.PaymentLog.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PaymentLogTest extends SuperValidationTests<PaymentLog> {

    @Test
    void validatePaymentInPaymentLog() {
        PaymentLog mockPaymentLogWithNullPayment = MockPaymentLog.builder()
                .payment(null)
                .build();

        PaymentLog mockPaymentLogWithPayment = MockPaymentLog.builder()
                .payment(MockPayment.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockPaymentLogWithNullPayment)).containsExactlyInAnyOrder(PAYMENT_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPaymentLogWithPayment)).isEmpty()
        );
    }

    @Test
    void validateMethodInPaymentLog() {
        PaymentLog mockPaymentLogWithNullMethod = MockPaymentLog.builder()
                .method(null)
                .build();

        Set<PaymentLog> mockPaymentLogsWithEnumMethods = Arrays.stream(PaymentMethod.values()).map(method ->
                        MockPaymentLog.builder()
                                .method(method)
                                .build())
                .collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(mockPaymentLogWithNullMethod)).containsExactlyInAnyOrder(METHOD_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPaymentLogsWithEnumMethods)).isEmpty()
        );
    }

    @Test
    void validateTypeInPaymentLog() {
        PaymentLog mockPaymentLogWithNullType = MockPaymentLog.builder()
                .actionType(null)
                .build();

        Set<PaymentLog> mockPaymentLogsWithEnumTypes = Arrays.stream(PaymentActionType.values()).map(type ->
                        MockPaymentLog.builder()
                                .actionType(type)
                                .build())
                .collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(mockPaymentLogWithNullType)).containsExactlyInAnyOrder(TYPE_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPaymentLogsWithEnumTypes)).isEmpty()
        );
    }

    @Test
    void validateAmountInPaymentLog() {
        PaymentLog mockPaymentLogWithNullAmount = MockPaymentLog.builder()
                .amount(null)
                .build();

        int negativeAmount = -1000;
        PaymentLog mockPaymentLogWithNegativeAmount = MockPaymentLog.builder()
                .amount(negativeAmount)
                .build();

        Set<PaymentLog> mockPointsWithPositiveAmounts = Stream.of(0, 10000, 30000).map(
                amount -> MockPaymentLog.builder()
                        .amount(amount)
                        .build())
                .collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(mockPaymentLogWithNullAmount)).containsExactlyInAnyOrder(AMOUNT_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPaymentLogWithNegativeAmount)).containsExactlyInAnyOrder(formatPositive(AMOUNT_HAS_TO_BE_POSITIVE, negativeAmount)),
                () -> assertThat(failureMessagesOf(mockPointsWithPositiveAmounts)).isEmpty()
        );
    }

    @Test
    void validatePointInPaymentLog() {
        PaymentLog mockPaymentLogWithNullPoint = MockPaymentLog.builder()
                .point(null)
                .build();

        PaymentLog mockPaymentLogWithPoint = MockPaymentLog.builder()
                .point(MockPoint.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockPaymentLogWithNullPoint)).containsExactlyInAnyOrder(POINT_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPaymentLogWithPoint)).isEmpty()
        );
    }
}
