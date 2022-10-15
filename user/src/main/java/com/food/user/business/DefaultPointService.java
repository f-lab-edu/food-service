package com.food.user.business;

import com.food.common.user.business.external.PointService;
import com.food.common.user.business.external.model.PointsUseRequest;
import com.food.common.user.business.internal.PointCommonService;
import com.food.common.user.business.internal.dto.PointDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultPointService implements PointService {
    private final PointCommonService pointCommonService;

    @Override
    public Long use(PointsUseRequest request) {
        PointDto userPoints = pointCommonService.findLatestPointByUserId(request.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("잔여포인트가 부족합니다. 잔여포인트=" + 0));

        if (request.canUsePointsIn(userPoints)) {
            throw new IllegalArgumentException("잔여포인트가 부족합니다. 잔여포인트=" + userPoints.getCurrentAmount());
        }

        return pointCommonService.save(request.toPointSaveDto());
    }
}
