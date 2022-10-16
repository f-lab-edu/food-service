package com.food.common.payment.business.internal.model;

import com.food.common.payment.enumeration.PaymentActionType;
import lombok.Getter;

@Getter
public final class PaymentSaveDto {
    private final Long orderId;
    private final PaymentActionType actionType;

    public PaymentSaveDto(Long orderId, PaymentActionType actionType) {
        this.orderId = orderId;
        this.actionType = actionType;
    }
}
