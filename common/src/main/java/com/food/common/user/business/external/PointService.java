package com.food.common.user.business.external;

import com.food.common.user.business.external.model.PointCollectRequest;
import com.food.common.user.business.external.model.PointUseRequest;

import javax.validation.constraints.NotNull;

public interface PointService {
    Long use(@NotNull PointUseRequest request);

    void collect(@NotNull PointCollectRequest request);
}
