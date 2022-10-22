package com.food.mock.payment;

import com.food.common.payment.domain.Payment;
import com.food.common.payment.domain.PaymentLog;
import com.food.common.payment.repository.PaymentLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockPaymentLogFactory {
    @Autowired
    private PaymentLogRepository paymentLogRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public PaymentLog paymentLog(Payment payment, int amount) {
        PaymentLog log = MockPaymentLog.builder()
                .payment(payment)
                .amount(amount)
                .build();

        return paymentLogRepository.save(log);
    }
}
