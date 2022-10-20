package com.food.common.user.business.internal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class PointSaveDto {
    private Long usedId;
    private Integer amount;

    public static PointSaveDto createUseRequest(Long userId, Integer amount) {
        PointSaveDto pointSaveDto = new PointSaveDto();
        pointSaveDto.usedId = userId;
        pointSaveDto.amount = amount;

        return pointSaveDto;
    }
}
