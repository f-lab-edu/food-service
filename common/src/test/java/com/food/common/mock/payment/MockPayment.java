package com.food.common.mock.payment;

import com.food.common.mock.order.MockOrder;
import com.food.common.order.domain.Order;
import com.food.common.payment.domain.Payment;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockPayment {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private Order order = MockOrder.builder().build();
        private Payment.Status status = Payment.Status.CANCELLATION_COMPLETED;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder status(Payment.Status status) {
            this.status = status;
            return this;
        }

        public Payment build() {
            return Payment.create(order, status);
        }
    }
}
