package com.food.order.business.model;

import com.food.common.order.business.model.OrderDto;
import com.food.common.order.business.model.OrderMenuDto;
import com.food.common.order.enumeration.OrderStatus;
import com.food.common.store.business.internal.dto.StoreDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Validated
public class Order {
    private final Long customerId;

    @NotNull
    private final StoreDto store;

    private final OrderStatus status = OrderStatus.REQUEST;

    @NotNull @Size(min = 1)
    private final List<OrderMenu> menus;

    private final String comment;

    private Long savedOrderId;

    public Order(Long customerId, StoreDto store, List<OrderMenu> menus, String comment) {
        this.customerId = customerId;
        this.store = store;
        this.menus = menus;
        this.comment = comment;
    }

    public boolean hasGreaterTotalPriceThanMinOrderAmount() {
        return orderAmount() >= store.getMinOrderAmount();
    }

    public Integer orderAmount() {
        return menus.stream()
                .map(OrderMenu::getOrderMenuAmount)
                .reduce(0, Integer::sum);
    }

    public OrderDto toOrderDto() {
        return new OrderDto(customerId, store.getStoreId(), orderAmount(), status, comment);
    }

    public List<OrderMenuDto> toOrderMenuDtos() {
        return menus.stream()
                .map(OrderMenu::toOrderMenuDto)
                .collect(Collectors.toList());
    }
}
