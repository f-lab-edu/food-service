package com.food.common.integration.domain;

import com.food.common.common.domain.Point;
import com.food.common.payment.domain.Payment;
import com.food.common.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentValidationTest extends DomainValidationIntegrationTest {
    private Set<ConstraintViolation<Payment>> getViolationsOfPaymentConstructor(Integer amount, Payment.Method method, Payment.Status status, Payment.PaymentPoints points) throws NoSuchMethodException {
        Constructor<Payment> constructor = Payment.class.getConstructor(Integer.class, Payment.Method.class, Payment.Status.class, Payment.PaymentPoints.class);

        Object[] parameterValues = {amount, method, status, points};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @Test
    @DisplayName("Payment 생성자에 Amount를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullAmount_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Payment>> constraintViolations =
                getViolationsOfPaymentConstructor(null, Payment.Method.CARD, Payment.Status.PAYMENT_COMPLETED, createDummyPaymentPoints());

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -1000})
    @DisplayName("Payment 생성자에 Amount을 음수로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankAmount_thenCorrectNumberOfViolations(Integer amount) throws NoSuchMethodException {
        Set<ConstraintViolation<Payment>> constraintViolations =
                getViolationsOfPaymentConstructor(amount, Payment.Method.CARD, Payment.Status.PAYMENT_COMPLETED, createDummyPaymentPoints());

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Payment 생성자에 Method를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullMethod_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Payment>> constraintViolations =
                getViolationsOfPaymentConstructor(24000, null, Payment.Status.PAYMENT_COMPLETED, createDummyPaymentPoints());

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Payment 생성자에 Status를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullStatus_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Payment>> constraintViolations =
                getViolationsOfPaymentConstructor(24000, Payment.Method.CARD, null, createDummyPaymentPoints());

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Payment 생성자에 PaymentPoints를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullPaymentPoints_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Payment>> constraintViolations =
                getViolationsOfPaymentConstructor(24000, Payment.Method.CARD, Payment.Status.PAYMENT_COMPLETED, null);

        assertEquals(constraintViolations.size(), 1);
    }

    private Payment.PaymentPoints createDummyPaymentPoints() {
        return Payment.PaymentPoints.notUse(createDummyUser());
    }

    private User createDummyUser() {
        return new User("userA", "passwordABCD", "userA", new Point(1000));
    }
}
