package com.food.common.order.business.internal.impl;

import com.food.common.order.domain.Order;
import com.food.common.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderEntityService {
    private final OrderRepository orderRepository;

    public Order findById(@NotNull Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("order를 찾을 수 없습니다. orderId=" + id));
    }
}
