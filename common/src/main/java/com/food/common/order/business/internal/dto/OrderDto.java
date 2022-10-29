package com.food.common.order.business.internal.dto;

import com.food.common.order.enumeration.OrderStatus;
import com.food.common.order.domain.Order;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public final class OrderDto {
    private final Long id;
    private final Long customerId;
    private final Long storeId;
    private final Integer amount;
    private final OrderStatus status;
    private final String comment;

    public OrderDto(@NotNull final Order order) {
        this.id = order.getId();
        this.customerId = order.getCustomerId();
        this.storeId = order.getStoreId();
        this.amount = order.getAmount();
        this.status = order.getStatus();
        this.comment = order.getComment();
    }

    public OrderDto(Long customerId, Long storeId, Integer amount, OrderStatus status, String comment) {
        this.id = null;
        this.customerId = customerId;
        this.storeId = storeId;
        this.amount = amount;
        this.status = status;
        this.comment = comment;
    }
}
