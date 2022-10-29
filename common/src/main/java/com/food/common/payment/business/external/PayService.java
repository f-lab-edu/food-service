package com.food.common.payment.business.external;

import com.food.common.payment.business.external.model.PayRequest;
import com.food.common.user.business.external.model.RequestUser;

public interface PayService {
    Long pay(PayRequest request);

    void cancelPayment(Long paymentId, RequestUser requestUser);
}