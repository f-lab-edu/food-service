package com.food.common.user.business.internal.impl;

import com.food.common.payment.business.internal.impl.PaymentEntityService;
import com.food.common.payment.domain.Payment;
import com.food.common.user.business.internal.PointCommonService;
import com.food.common.user.business.internal.dto.PointDto;
import com.food.common.user.business.internal.dto.PointSaveDto;
import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
import com.food.common.user.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultPointCommonService implements PointCommonService {
    private final PointRepository pointRepository;
    private final UserEntityService userEntityService;
    private final PaymentEntityService paymentEntityService;

    @Override
    public Optional<PointDto> findLatestPointByUserId(Long userId) {
        Optional<Point> optionalPoint = findLatestPointByUser(findUser(userId));

        if (optionalPoint.isEmpty()) return Optional.empty();

        return Optional.of(new PointDto(optionalPoint.get()));
    }

    @Override
    public Long save(PointSaveDto request) {
        User user = findUser(request.getUsedId());

        Point basePoint = findLatestPointByUser(user)
                .orElse(Point.createFirstPoint(user));

        Point changedPoint = createChangedPoint(request, basePoint);

        return pointRepository.save(changedPoint).getId();
    }

    private Point createChangedPoint(PointSaveDto request, Point basePoint) {
        Point result;
        switch (request.getType()) {
            case COLLECT -> {
                Payment payment = paymentEntityService.findById(request.getPaymentId());
                result = basePoint.collect(request.getAmount(), payment);
            }
            case USE -> {
                result = basePoint.use(request.getAmount());
            }
            case RETRIEVE -> {
                Payment payment = paymentEntityService.findById(request.getPaymentId());
                result = basePoint.retrieve(request.getAmount(), payment);
            }
            case RECOLLECT -> {
                result = basePoint.recollect(request.getAmount());
            }
            default -> throw new IllegalStateException("Unexpected value: " + request.getType());
        }
        return result;
    }


    @Override
    public Optional<PointDto> findByPointId(Long pointId) {
        return pointRepository.findById(pointId)
                .map(PointDto::new);
    }

    @Override
    public List<PointDto> findAllByPaymentId(Long paymentId) {
        Payment payment = paymentEntityService.findById(paymentId);

        return pointRepository.findAllByPaymentOrderByCreatedDateDesc(payment).stream()
                .map(PointDto::new)
                .collect(Collectors.toList());
    }

    private Optional<Point> findLatestPointByUser(User user) {
        return pointRepository.findFirstByUserOrderByCreatedDateDesc(user);
    }

    private User findUser(Long userId) {
        return userEntityService.findById(userId);
    }
}
