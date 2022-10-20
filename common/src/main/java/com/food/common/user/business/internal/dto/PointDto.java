package com.food.common.user.business.internal.dto;

import com.food.common.user.domain.Point;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public final class PointDto {
    private final Long id;
    private final Long userId;
    private final Point.Type type;
    private final Integer changedAmount;
    private final Integer currentAmount;
    private final Long paymentId;

    public PointDto(@NotNull final Point point) {
        this.id = point.getId();
        this.userId = point.getUserId();
        this.type = point.getType();
        this.changedAmount = point.getChangedAmount();
        this.currentAmount = point.getCurrentAmount();
        this.paymentId = point.getPaymentId();
    }
}
