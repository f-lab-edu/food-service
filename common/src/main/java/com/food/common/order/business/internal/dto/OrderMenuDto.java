package com.food.common.order.business.internal.dto;

import com.food.common.order.domain.OrderMenu;
import lombok.Getter;

@Getter
public final class OrderMenuDto {
    private final Long id;
    private final Long orderId;
    private final Long menuId;
    private final Integer amount;
    private final Byte count;

    public OrderMenuDto(OrderMenu entity) {
        id = entity.getId();
        orderId = entity.getOrderId();
        menuId = entity.getMenuId();
        amount = entity.getAmount();
        count = entity.getCount();
    }

    public OrderMenuDto(Long menuId, Integer amount, Byte count) {
        this.id = null;
        this.orderId = null;
        this.menuId = menuId;
        this.amount = amount;
        this.count = count;
    }

    public Long getMenuId() {
        return menuId;
    }
}
