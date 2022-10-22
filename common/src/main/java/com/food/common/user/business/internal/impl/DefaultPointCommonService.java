package com.food.common.user.business.internal.impl;

import com.food.common.payment.business.internal.impl.PaymentEntityService;
import com.food.common.payment.domain.Payment;
import com.food.common.user.business.internal.PointCommonService;
import com.food.common.user.business.internal.dto.PointDto;
import com.food.common.user.business.internal.dto.PointSaveDto;
import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
import com.food.common.user.enumeration.PointType;
import com.food.common.user.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        if (request.getType() == PointType.USE) {
            Point usedPoint = basePoint.use(request.getAmount());
            return pointRepository.save(usedPoint).getId();
        } else {
            Payment payment = paymentEntityService.findById(request.getPaymentId());
            Point collectedPoint = basePoint.collect(request.getAmount(), payment);
            return pointRepository.save(collectedPoint).getId();
        }
    }

    private Optional<Point> findLatestPointByUser(User user) {
        return pointRepository.findFirstByUserOrderByCreatedDateDesc(user);
    }

    private User findUser(Long userId) {
        return userEntityService.findById(userId);
    }
}
