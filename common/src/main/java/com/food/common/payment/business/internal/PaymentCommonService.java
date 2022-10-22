package com.food.common.payment.business.internal;

import com.food.common.payment.business.internal.model.PaymentSaveDto;
import com.food.common.payment.enumeration.PaymentActionType;

import javax.validation.constraints.NotNull;

public interface PaymentCommonService {
    Long save(@NotNull PaymentSaveDto request);

    void updateActionType(@NotNull Long paymentId, @NotNull PaymentActionType actionType);
}
