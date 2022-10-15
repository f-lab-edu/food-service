package com.food.common.user.business.external;

import com.food.common.user.business.external.model.PointsUseRequest;

import javax.validation.constraints.NotNull;

public interface PointService {
    Long use(@NotNull PointsUseRequest pointsUseRequest);
}
