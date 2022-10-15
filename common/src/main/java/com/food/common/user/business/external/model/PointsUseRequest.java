package com.food.common.user.business.external.model;

import com.food.common.user.business.internal.dto.PointDto;
import com.food.common.user.business.internal.dto.PointSaveDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class PointsUseRequest {
    @NotNull @Positive
    private Integer amount;

    @NotNull
    private Long ownerId;

    @Builder
    public PointsUseRequest(Integer amount, Long ownerId) {
        this.amount = amount;
        this.ownerId = ownerId;
    }

    public boolean canUsePointsIn(@NotNull PointDto points) {
        return points.getCurrentAmount() >= amount;
    }

    public PointSaveDto toPointSaveDto() {
        return PointSaveDto.createUseRequest(ownerId, amount);
    }
}
