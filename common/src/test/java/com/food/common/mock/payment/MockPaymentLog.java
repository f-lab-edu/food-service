package com.food.common.mock.payment;

import com.food.common.mock.user.MockPoint;
import com.food.common.payment.domain.Payment;
import com.food.common.payment.domain.PaymentLog;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
import com.food.common.user.domain.Point;

public class MockPaymentLog {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Payment payment = MockPayment.builder().build();
        private PaymentMethod method = PaymentMethod.CARD;
        private PaymentActionType type = PaymentActionType.PAYMENT;
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

        public Builder actionType(PaymentActionType type) {
            this.type = type;
            return this;
        }

        public Builder method(PaymentMethod method) {
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
            return PaymentLog.create(payment, method, amount, point);
        }
    }
}
