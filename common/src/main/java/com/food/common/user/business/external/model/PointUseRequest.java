package com.food.common.user.business.external.model;

import com.food.common.user.business.internal.dto.PointSaveDto;
import com.food.common.user.enumeration.PointType;
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

    public boolean hasGreaterAmountThan(int currentAmount) {
        return amount > currentAmount;
    }

    public PointSaveDto toPointSaveDto() {
        return PointSaveDto.builder()
                .usedId(ownerId)
                .amount(amount)
                .type(PointType.USE)
                .build();
    }
}
