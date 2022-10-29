package com.food.user.business;

import com.food.common.user.business.external.PointService;
import com.food.common.user.business.external.model.PointCollectRequest;
import com.food.common.user.business.external.model.PointUseRequest;
import com.food.common.user.business.internal.PointCommonService;
import com.food.common.user.business.internal.dto.PointDto;
import com.food.common.user.business.internal.dto.PointSaveDto;
import com.food.common.user.enumeration.PointType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultPointService implements PointService {
    private final PointCommonService pointCommonService;

    @Override
    public Long use(PointUseRequest request) {
        int currentAmount = currentAmount(request.getOwnerId());
        if (request.hasGreaterAmountThan(currentAmount)) {
            throw new IllegalArgumentException("잔여포인트가 부족합니다. 잔여포인트=" + currentAmount);
        }

        return pointCommonService.save(request.toPointSaveDto());
    }

    @Override
    public void collect(PointCollectRequest request) {
        int collectAmount = calculateCollectAmount(0.05F, request.getPaymentAmount());

        pointCommonService.save(request.toPointSaveDto(collectAmount));
    }

    @Override
    public void recollectUsedPoint(Long pointId) {
        Optional<PointDto> optionalPoint = pointCommonService.findByPointId(pointId);
        if (optionalPoint.isEmpty()) return;

        PointDto point = optionalPoint.get();
        PointSaveDto request = PointSaveDto.builder()
                .usedId(point.getUserId())
                .amount(point.getChangedAmount())
                .type(PointType.RECOLLECT)
                .paymentId(point.getPaymentId())
                .build();

        pointCommonService.save(request);
    }

    @Override
    public void retrieveCollectedPoint(Long paymentId) {
        List<PointDto> pointHistory = pointCommonService.findAllByPaymentId(paymentId);
        PointDto point = getValidatedRetrievedPoint(pointHistory);

        PointSaveDto request = PointSaveDto.builder()
                .usedId(point.getUserId())
                .amount(point.getChangedAmount())
                .type(PointType.RETRIEVE)
                .paymentId(paymentId)
                .build();

        pointCommonService.save(request);
    }

    private PointDto getValidatedRetrievedPoint(List<PointDto> pointHistory) {
        boolean existsRetrieveHistory = pointHistory.stream()
                .anyMatch(point -> point.getType() == PointType.RETRIEVE);

        if (existsRetrieveHistory) {
            throw new IllegalArgumentException("이미 회수내역이 존재합니다.");
        }

        return pointHistory.stream()
                .filter(point -> point.getType() == PointType.COLLECT)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("적립내역이 존재하지 않습니다."));
    }

    private int calculateCollectAmount(float accumulationRate, int paymentAmount) {
        return (int) Math.ceil(paymentAmount * accumulationRate);
    }

    private int currentAmount(@NotNull Long userId) {
        Optional<PointDto> point = pointCommonService.findLatestPointByUserId(userId);
        return point.isPresent() ? point.get().getCurrentAmount() : 0;
    }
}
