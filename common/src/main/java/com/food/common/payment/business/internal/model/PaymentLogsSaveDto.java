package com.food.common.payment.business.internal.model;

import com.food.common.payment.enumeration.PaymentMethod;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
public final class PaymentLogsSaveDto {
    @NotNull
    private final Long paymentId;

    @NotNull @Size(min = 1)
    private final Set<PaymentLog> logs;

    public PaymentLogsSaveDto(Long paymentId, Set<PaymentLog> logs) {
        this.paymentId = paymentId;
        this.logs = logs;
    }

    @Getter
    public static final class PaymentLog {
        @NotNull
        private final PaymentMethod method;

        @NotNull @Positive
        private final Integer amount;

        private Long pointId;

        public PaymentLog(PaymentMethod method, Integer amount) {
            this.method = method;
            this.amount = amount;
        }

        public PaymentLog(PaymentMethod method, Integer amount, Long pointId) {
            this(method, amount);
            this.pointId = pointId;

            validateOfPointPayment();
        }

        private void validateOfPointPayment() {
            if (method == PaymentMethod.POINT && pointId != null) return;

            throw new IllegalArgumentException("포인트 지급내역이 존재하지 않습니다.");
        }

        public boolean isPoint() {
            return method == PaymentMethod.POINT;
        }
    }
}
