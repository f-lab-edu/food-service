package com.food.common.user.business.internal.dto;

import com.food.common.user.enumeration.PointType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PointSaveDto {
    private Long usedId;
    private Integer amount;
    private PointType type;
    private Long paymentId;

    @Builder
    public PointSaveDto(Long usedId, Integer amount, PointType type, Long paymentId) {
        this.usedId = usedId;
        this.amount = amount;
        this.type = type;
        this.paymentId = paymentId;
    }
}
