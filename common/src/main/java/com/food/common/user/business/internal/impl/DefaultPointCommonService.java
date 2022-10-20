package com.food.common.user.business.internal.impl;

import com.food.common.user.business.internal.PointCommonService;
import com.food.common.user.business.internal.dto.PointDto;
import com.food.common.user.business.internal.dto.PointSaveDto;
import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
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

    @Override
    public Optional<PointDto> findLatestPointByUserId(Long userId) {
        Optional<Point> optionalPoint = findLatestPointByUser(findUser(userId));

        if (optionalPoint.isEmpty()) return Optional.empty();

        return Optional.of(new PointDto(optionalPoint.get()));
    }

    @Override
    public Long save(PointSaveDto request) {
        User user = findUser(request.getUsedId());

        Point point = Point.create(user, Point.Type.USE, request.getAmount(), getCurrentPoints(user));
        return pointRepository.save(point).getId();
    }

    private Integer getCurrentPoints(User user) {
        Optional<Point> optionalPoint = findLatestPointByUser(user);
        if (optionalPoint.isEmpty()) return 0;

        return optionalPoint.get().getCurrentAmount();
    }

    private Optional<Point> findLatestPointByUser(User user) {
        return pointRepository.findFirstByUserOrderByCreatedDateDesc(user);
    }

    private User findUser(Long userId) {
        return userEntityService.findById(userId);
    }
}
