package com.food.common.user.business.internal;

import com.food.common.user.business.internal.dto.PointDto;
import com.food.common.user.business.internal.dto.PointSaveDto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PointCommonService {
    Optional<PointDto> findLatestPointByUserId(@NotNull Long userId);

    Long save(@NotNull PointSaveDto request);

    Optional<PointDto> findByPointId(@NotNull Long pointId);

    /**
     * 해당 결제 건에 관련된 포인트 정보를 모두 가져온다. (정렬기준: 최신순)
     */
    List<PointDto> findAllByPaymentId(@NotNull Long paymentId);
}
