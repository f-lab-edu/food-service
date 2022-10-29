package com.food.mock.payment;

import com.food.common.order.domain.Order;
import com.food.common.payment.domain.Payment;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockPaymentFactory {

    @Autowired
    private PaymentRepository paymentRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public Payment payment(Order order) {
        Payment payment = MockPayment.builder()
                .order(order)
                .actionType(PaymentActionType.PAYMENT)
                .build();

        return paymentRepository.save(payment);
    }
}
