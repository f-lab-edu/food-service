package com.food.mock.payment;

import com.food.common.payment.domain.Payment;
import com.food.common.payment.domain.PaymentLog;
import com.food.common.user.domain.Point;
import com.food.mock.user.MockPoint;

public class MockPaymentLog {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Payment payment = MockPayment.builder().build();
        private PaymentLog.Method method = PaymentLog.Method.CARD;
        private PaymentLog.Type type = PaymentLog.Type.PAYMENT;
        private Integer amount = 24000;
        private Point point = MockPoint.builder().build();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder payment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder type(PaymentLog.Type type) {
            this.type = type;
            return this;
        }

        public Builder method(PaymentLog.Method method) {
            this.method = method;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder point(Point point) {
            this.point = point;
            return this;
        }

        public PaymentLog build() {
            return PaymentLog.create(payment, method, type, amount, point);
        }
    }
}
