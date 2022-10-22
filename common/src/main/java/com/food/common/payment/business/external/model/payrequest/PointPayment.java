package com.food.common.payment.business.external.model.payrequest;

import com.food.common.payment.business.internal.model.PaymentLogsSaveDto;
import com.food.common.payment.enumeration.PaymentMethod;
import com.food.common.user.business.external.model.PointUseRequest;

import javax.validation.constraints.NotNull;

public final class PointPayment extends PaymentElement {
    private Long pointId;

    private Long payerId;

    public PointPayment(Integer amount, Long payerId) {
        super(PaymentMethod.POINT, amount);
        this.payerId = payerId;
    }

    public void updateUsedPointId(@NotNull Long pointId) {
        this.pointId = pointId;
    }

    public PointUseRequest toPointsUseRequest() {
        return PointUseRequest.builder()
                .ownerId(payerId)
                .amount(amount)
                .build();
    }

    public PaymentLogsSaveDto.PaymentLog toLogOfPaymentLogsSaveDto() {
        return new PaymentLogsSaveDto.PaymentLog(method, amount, pointId);
    }
}
