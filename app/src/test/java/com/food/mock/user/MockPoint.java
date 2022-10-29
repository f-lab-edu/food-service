package com.food.mock.user;

import com.food.common.payment.domain.Payment;
import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
import com.food.common.user.enumeration.PointType;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockPoint {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private User user = MockUser.builder().build();
        private PointType type = PointType.COLLECT;
        private Integer changedAmount = 100;
        private Integer currentAmount = 1000;
        private Payment payment;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder type(PointType type) {
            this.type = type;
            return this;
        }

        public Builder changedAmount(Integer changedAmount) {
            this.changedAmount = changedAmount;
            return this;
        }

        public Builder currentAmount(Integer currentAmount) {
            this.currentAmount = currentAmount;
            return this;
        }

        public Builder payment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Point build() {
            return Point.create(user, type, changedAmount, currentAmount, payment);
        }
    }
}
