package com.food.common.store.business.internal.dto;

import com.food.common.store.domain.Store;
import com.food.common.store.enumeration.StoreOpenStatus;
import lombok.Getter;

@Getter
public final class StoreDto {
    private final Long storeId;
    private final String name;
    private final Long storeOwnerId;
    private final Integer minOrderAmount;
    private final StoreOpenStatus openStatus;

    public StoreDto(Store entity) {
        this.storeId = entity.getId();
        this.name = entity.getName();
        this.storeOwnerId = entity.getOwnerId();
        this.minOrderAmount = entity.getMinOrderAmount();
        this.openStatus = entity.getStatus();
    }

    public boolean isClosed() {
        return openStatus == StoreOpenStatus.CLOSED;
    }
}
