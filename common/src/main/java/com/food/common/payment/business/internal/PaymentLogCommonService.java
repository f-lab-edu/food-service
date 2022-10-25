package com.food.common.payment.business.internal;

import com.food.common.payment.business.external.model.payrequest.PaymentElement;
import com.food.common.payment.domain.PaymentLog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public interface PaymentLogCommonService {
    void saveAll(@NotNull Long paymentId, @NotNull @Size(min = 1) Set<PaymentElement> elements);

    List<PaymentLog> findAllByPaymentId(@NotNull Long paymentId);
}
