package com.food.common.payment.business.internal;

import com.food.common.payment.business.internal.model.PaymentLogsSaveDto;
import com.food.common.payment.domain.PaymentLog;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface PaymentLogCommonService {
    void saveAll(@NotNull PaymentLogsSaveDto request);

    List<PaymentLog> findAllByPaymentId(@NotNull Long paymentId);
}
