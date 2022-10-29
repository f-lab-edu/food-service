package com.food.common.order.business.internal.impl;

import com.food.common.order.business.internal.OrderCommonService;
import com.food.common.order.business.internal.dto.OrderDto;
import com.food.common.order.domain.Order;
import com.food.common.order.repository.OrderRepository;
import com.food.common.store.business.internal.impl.StoreEntityService;
import com.food.common.store.domain.Store;
import com.food.common.user.business.internal.impl.UserEntityService;
import com.food.common.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultOrderCommonService implements OrderCommonService {
    private final OrderRepository orderRepository;
    private final StoreEntityService storeEntityService;
    private final UserEntityService userEntityService;

    @Override
    public Optional<OrderDto> findById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new OrderDto(optionalOrder.get()));
    }

    @Override
    public Long save(OrderDto order) {
        User customer = userEntityService.findById(order.getCustomerId());
        Store store = storeEntityService.findById(order.getStoreId());

        Order entity = Order.create(customer, store, order.getAmount(), order.getStatus(), order.getComment());
        return orderRepository.save(entity).getId();
    }
}
