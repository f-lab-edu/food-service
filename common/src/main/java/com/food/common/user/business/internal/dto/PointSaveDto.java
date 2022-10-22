package com.food.common.user.business.internal.dto;

import com.food.common.user.enumeration.PointType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class PointSaveDto {
    private Long usedId;
    private Integer amount;
    private PointType type;
    private Long paymentId;

    public static PointSaveDto createUseRequest(Long userId, Integer amount) {
        PointSaveDto pointSaveDto = new PointSaveDto();
        pointSaveDto.usedId = userId;
        pointSaveDto.amount = amount;
        pointSaveDto.type = PointType.USE;

        return pointSaveDto;
    }

    public static PointSaveDto createCollectRequest(Long userId, Integer amount, Long paymentId) {
        PointSaveDto pointSaveDto = new PointSaveDto();
        pointSaveDto.usedId = userId;
        pointSaveDto.amount = amount;
        pointSaveDto.type = PointType.COLLECT;
        pointSaveDto.paymentId = paymentId;

        return pointSaveDto;
    }
}
