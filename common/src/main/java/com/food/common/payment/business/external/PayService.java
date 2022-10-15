package com.food.common.payment.business.external;

import com.food.common.payment.business.external.model.PayRequest;

public interface PayService {
    void pay(PayRequest request);
}