package com.food.common.user.business.internal;

import com.food.common.user.business.internal.dto.PointDto;
import com.food.common.user.business.internal.dto.PointSaveDto;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PointCommonService {
    Optional<PointDto> findLatestPointByUserId(@NotNull Long userId);

    Long save(@NotNull PointSaveDto request);
}
