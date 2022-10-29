package com.food.common.order.business.internal.dto;

public class OrderMenuSelectionDto {
    private final Long id;
    private final Long menuId;
    private final Long menuSelectionId;

    public OrderMenuSelectionDto(Long menuId, Long menuSelectionId) {
        this.id = null;
        this.menuId = menuId;
        this.menuSelectionId = menuSelectionId;
    }
}
