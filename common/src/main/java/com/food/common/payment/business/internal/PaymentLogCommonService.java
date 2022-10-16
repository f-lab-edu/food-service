package com.food.common.payment.business.internal;

import com.food.common.payment.business.internal.model.PaymentLogsSaveDto;

import javax.validation.constraints.NotNull;

public interface PaymentLogCommonService {
    void saveAll(@NotNull PaymentLogsSaveDto request);
}
