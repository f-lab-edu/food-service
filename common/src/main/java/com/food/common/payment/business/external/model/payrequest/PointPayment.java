package com.food.common.payment.business.external.model.payrequest;

import com.food.common.payment.enumeration.PaymentMethod;
import com.food.common.user.business.external.model.PointUseRequest;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public final class PointPayment extends PaymentElement {
    private Long pointId;

    public PointPayment(Integer amount) {
        super(amount);
    }

    public void updateUsedPointId(@NotNull Long pointId) {
        this.pointId = pointId;
    }

    public PointUseRequest toPointsUseRequest(Long ownerId) {
        return PointUseRequest.builder()
                .ownerId(ownerId)
                .amount(amount)
                .build();
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.POINT;
    }

    public void validate() {
        if (pointId == null) {
            throw new IllegalArgumentException("포인트 지급내역이 존재하지 않습니다.");
        }
    }
}
