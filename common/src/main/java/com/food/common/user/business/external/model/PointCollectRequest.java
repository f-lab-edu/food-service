package com.food.common.user.business.external.model;

import com.food.common.user.business.internal.dto.PointSaveDto;
import com.food.common.user.enumeration.PointType;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
public class PointCollectRequest {
    @NotNull
    private final Long ownerId;

    @NotNull
    private final Long paymentId;

    @NotNull @Positive
    private final Integer paymentAmount;

    public PointCollectRequest(Long ownerId, Long paymentId, Integer paymentAmount) {
        this.ownerId = ownerId;
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
    }

    public PointSaveDto toPointSaveDto(int collectAmount) {
        return PointSaveDto.builder()
                .usedId(ownerId)
                .amount(collectAmount)
                .type(PointType.COLLECT)
                .paymentId(paymentId)
                .build();
    }
}
