package com.food.common.user.business.external.model;

import com.food.common.user.business.internal.dto.PointSaveDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class PointUseRequest {
    @NotNull @Positive
    private final Integer amount;

    @NotNull
    private final Long ownerId;

    @Builder
    public PointUseRequest(Integer amount, Long ownerId) {
        this.amount = amount;
        this.ownerId = ownerId;
    }

    public boolean hasLessThan(int currentAmount) {
        return currentAmount > amount;
    }

    public PointSaveDto toPointSaveDto() {
        return PointSaveDto.createUseRequest(ownerId, amount);
    }
}
