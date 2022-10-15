package com.food.common.order.business.internal.impl;

import com.food.common.order.business.internal.OrderCommonService;
import com.food.common.order.business.internal.dto.OrderDto;
import com.food.common.order.domain.Order;
import com.food.common.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultOrderCommonService implements OrderCommonService {
    private final OrderRepository orderRepository;

    @Override
    public Optional<OrderDto> findById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new OrderDto(optionalOrder.get()));
    }
}
