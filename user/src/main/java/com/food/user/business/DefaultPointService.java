package com.food.user.business;

import com.food.common.user.business.external.PointService;
import com.food.common.user.business.external.model.PointCollectRequest;
import com.food.common.user.business.external.model.PointUseRequest;
import com.food.common.user.business.internal.PointCommonService;
import com.food.common.user.business.internal.dto.PointDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultPointService implements PointService {
    private final PointCommonService pointCommonService;

    @Override
    public Long use(PointUseRequest request) {
        int currentAmount = currentAmount(request.getOwnerId());
        if (request.hasLessThan(currentAmount)) {
            throw new IllegalArgumentException("잔여포인트가 부족합니다. 잔여포인트=" + currentAmount);
        }

        return pointCommonService.save(request.toPointSaveDto());
    }

    @Override
    public void collect(PointCollectRequest request) {
        int collectAmount = calculateCollectAmount(0.05F, request.getPaymentAmount());

        pointCommonService.save(request.toPointSaveDto(collectAmount));
    }

    private int currentAmount(@NotNull Long userId) {
        Optional<PointDto> point = pointCommonService.findLatestPointByUserId(userId);
        return point.isPresent() ? point.get().getCurrentAmount() : 0;
    }

    public int calculateCollectAmount(float accumulationRate, int paymentAmount) {
        return (int) Math.ceil(paymentAmount * accumulationRate);
    }
}
