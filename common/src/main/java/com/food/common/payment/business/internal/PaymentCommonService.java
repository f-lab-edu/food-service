package com.food.common.payment.business.internal;

import com.food.common.payment.business.internal.model.PaymentSaveDto;

public interface PaymentCommonService {
    Long save(PaymentSaveDto request);
}
