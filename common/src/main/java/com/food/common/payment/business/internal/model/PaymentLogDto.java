package com.food.common.payment.business.internal.model;

import com.food.common.payment.domain.PaymentLog;
import com.food.common.payment.enumeration.PaymentMethod;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PaymentLogDto {
    private final Long id;
    private final Long paymentId;
    private final PaymentMethod method;
    private final Integer amount;
    private final Long pointId;

    public PaymentLogDto(@NotNull final PaymentLog entity) {
        id = entity.getId();
        paymentId = entity.getPaymentId();
        method = entity.getMethod();
        amount = entity.getAmount();
        pointId = entity.getPointId();
    }
}
