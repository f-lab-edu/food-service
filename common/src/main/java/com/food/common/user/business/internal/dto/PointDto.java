package com.food.common.user.business.internal.dto;

import com.food.common.user.domain.Point;
import com.food.common.user.enumeration.PointType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public final class PointDto {
    private Long id;
    private Long userId;
    private PointType type;
    private Integer changedAmount;
    private Integer currentAmount;
    private Long paymentId;

    public PointDto(@NotNull final Point point) {
        this.id = point.getId();
        this.userId = point.getUserId();
        this.type = point.getType();
        this.changedAmount = point.getChangedAmount();
        this.currentAmount = point.getCurrentAmount();
        this.paymentId = point.getPaymentId();
    }
}
