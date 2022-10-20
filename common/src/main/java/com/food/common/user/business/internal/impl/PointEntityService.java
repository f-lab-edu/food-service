package com.food.common.user.business.internal.impl;

import com.food.common.user.domain.Point;
import com.food.common.user.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Transactional
@Service
public class PointEntityService {
    private final PointRepository pointRepository;

    public Point findById(@NotNull Long id) {
        return pointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("point를 찾을 수 없습니다. id="+id));
    }
}
