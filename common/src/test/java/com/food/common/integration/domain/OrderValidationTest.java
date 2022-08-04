package com.food.common.integration.domain;

import com.food.common.common.domain.Point;
import com.food.common.order.domain.Order;
import com.food.common.payment.domain.Payment;
import com.food.common.store.domain.Store;
import com.food.common.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.util.Set;

import static com.food.common.integration.domain.MenuValidationTest.createDummyStore;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderValidationTest extends DomainValidationIntegrationTest {

    private Set<ConstraintViolation<Order>> getViolationsOfMenuOptionConstructor(User user, Store store, Integer amount, Order.Status status, String additionalComment, Payment payment) throws NoSuchMethodException {
        Constructor<Order> constructor = Order.class.getConstructor(User.class, Store.class, Integer.class, Order.Status.class, String.class, Payment.class);

        Object[] parameterValues = {user, store, amount, status, additionalComment, payment};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @Test
    @DisplayName("Order 생성자에 User를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullUser_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        User dummyUser = createDummyUser();
        Set<ConstraintViolation<Order>> constraintViolations =
                getViolationsOfMenuOptionConstructor(null, createDummyStore(), 24000, Order.Status.REQUESTED, null, createDummyPayment(dummyUser));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Order 생성자에 Store를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullStore_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        User dummyUser = createDummyUser();
        Set<ConstraintViolation<Order>> constraintViolations =
                getViolationsOfMenuOptionConstructor(dummyUser, null, 24000, Order.Status.REQUESTED, null, createDummyPayment(dummyUser));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Order 생성자에 Amount를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullAmount_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        User dummyUser = createDummyUser();
        Set<ConstraintViolation<Order>> constraintViolations =
                getViolationsOfMenuOptionConstructor(dummyUser, createDummyStore(), null, Order.Status.REQUESTED, null, createDummyPayment(dummyUser));

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -1000})
    @DisplayName("Order 생성자에 Amount을 음수로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankAmount_thenCorrectNumberOfViolations(Integer amount) throws NoSuchMethodException {
        User dummyUser = createDummyUser();
        Set<ConstraintViolation<Order>> constraintViolations =
                getViolationsOfMenuOptionConstructor(dummyUser, createDummyStore(), amount, Order.Status.REQUESTED, null, createDummyPayment(dummyUser));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Order 생성자에 Status를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullStatus_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        User dummyUser = createDummyUser();
        Set<ConstraintViolation<Order>> constraintViolations =
                getViolationsOfMenuOptionConstructor(dummyUser, createDummyStore(), 24000, null, null, createDummyPayment(dummyUser));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Order 생성자에 Payment를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullPayment_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        User dummyUser = createDummyUser();
        Set<ConstraintViolation<Order>> constraintViolations =
                getViolationsOfMenuOptionConstructor(dummyUser, createDummyStore(), 24000, Order.Status.REQUESTED, null, null);

        assertEquals(constraintViolations.size(), 1);
    }

    private User createDummyUser() {
        return new User("userA", "passwordABCD", "userA", new Point(1000));
    }

    private Payment createDummyPayment(User user) {
        return new Payment(24000, Payment.Method.CARD, Payment.Status.PAYMENT_COMPLETED, Payment.PaymentPoints.notUse(user));
    }
}
