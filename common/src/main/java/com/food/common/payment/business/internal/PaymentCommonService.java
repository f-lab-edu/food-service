package com.food.common.payment.business.internal;

import com.food.common.payment.enumeration.PaymentActionType;

import javax.validation.constraints.NotNull;

public interface PaymentCommonService {
    Long save(@NotNull Long orderId, @NotNull PaymentActionType actionType);

    void updateActionType(@NotNull Long paymentId, @NotNull PaymentActionType actionType);
}
