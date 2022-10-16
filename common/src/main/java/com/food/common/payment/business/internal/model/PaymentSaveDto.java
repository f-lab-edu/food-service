package com.food.common.payment.business.internal.model;

import com.food.common.payment.enumeration.PaymentActionType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public final class PaymentSaveDto {
    @NotNull
    private final Long orderId;

    @NotNull
    private final PaymentActionType actionType;

    public PaymentSaveDto(Long orderId, PaymentActionType actionType) {
        this.orderId = orderId;
        this.actionType = actionType;
    }
}
